package com.dreamsoftware.fitflextv.ui.screens.favorites

import com.dreamsoftware.fitflextv.di.FavoritesScreenErrorMapper
import com.dreamsoftware.fitflextv.domain.model.ITrainingProgramBO
import com.dreamsoftware.fitflextv.domain.model.TrainingTypeEnum
import com.dreamsoftware.fitflextv.domain.usecase.GetFavoritesTrainingsByUserUseCase
import com.dreamsoftware.fitflextv.domain.usecase.RemoveFavoriteTrainingUseCase
import com.dreamsoftware.fitflextv.ui.core.IErrorMapper
import com.dreamsoftware.fitflextv.ui.utils.toTrainingType
import com.dreamsoftware.fudge.core.FudgeViewModel
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesTrainingsByUserUseCase: GetFavoritesTrainingsByUserUseCase,
    private val removeFavoriteTrainingUseCase: RemoveFavoriteTrainingUseCase,
    @FavoritesScreenErrorMapper private val errorMapper: IErrorMapper,
) : FudgeViewModel<FavoritesUiState, FavoritesSideEffects>(), FavoritesScreenActionListener {

    override fun onGetDefaultState(): FavoritesUiState = FavoritesUiState()

    fun fetchData() {
        executeUseCase(
            useCase = getFavoritesTrainingsByUserUseCase,
            onSuccess = ::onGetFavoritesWorkoutsSuccessfully,
            onMapExceptionToState = ::onMapExceptionToState
        )
    }

    override fun onTrainingProgramSelected(trainingProgram: ITrainingProgramBO) {
        updateState { it.copy(trainingProgramSelected = trainingProgram) }
    }

    override fun onTrainingProgramStarted(id: String) {
        uiState.value.trainingProgramSelected?.let { trainingProgramSelected ->
            updateState { it.copy(trainingProgramSelected = null) }
            launchSideEffect(FavoritesSideEffects.OpenTrainingProgramDetail(
                id = trainingProgramSelected.id,
                type = trainingProgramSelected.toTrainingType()
            ))
        }
    }

    override fun onTrainingProgramRemovedFromFavorites(id: String) {
        executeUseCaseWithParams(
            useCase = removeFavoriteTrainingUseCase,
            params = RemoveFavoriteTrainingUseCase.Params(trainingId = id),
            onSuccess = ::onTrainingProgramRemovedFromFavoritesCompletedSuccessfully
        )
    }

    override fun onDismissRequest() {
        updateState { it.copy(trainingProgramSelected = null) }
    }

    private fun onGetFavoritesWorkoutsSuccessfully(trainingProgramList: List<ITrainingProgramBO>) {
        updateState { it.copy(favoritesTrainings = trainingProgramList) }
    }

    private fun onTrainingProgramRemovedFromFavoritesCompletedSuccessfully(isRemoved: Boolean) {
        if(isRemoved) {
            updateState { it.copy(
                favoritesTrainings = it.favoritesTrainings.filterNot { training -> training.id == it.trainingProgramSelected?.id },
                trainingProgramSelected = null
            ) }
        }
    }

    private fun onMapExceptionToState(ex: Exception, uiState: FavoritesUiState) =
        uiState.copy(
            isLoading = false,
            errorMessage = errorMapper.mapToMessage(ex)
        )
}

data class FavoritesUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val favoritesTrainings: List<ITrainingProgramBO> = emptyList(),
    val trainingProgramSelected: ITrainingProgramBO? = null
): UiState<FavoritesUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): FavoritesUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface FavoritesSideEffects: SideEffect {
    data class OpenTrainingProgramDetail(val id: String, val type: TrainingTypeEnum): FavoritesSideEffects
}