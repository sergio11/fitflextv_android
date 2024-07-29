package com.dreamsoftware.fitflextv.ui.screens.favorites

import com.dreamsoftware.fitflextv.di.FavoritesScreenErrorMapper
import com.dreamsoftware.fitflextv.domain.model.ITrainingProgramBO
import com.dreamsoftware.fitflextv.domain.usecase.GetFavoritesTrainingsByUserUseCase
import com.dreamsoftware.fitflextv.ui.core.BaseViewModel
import com.dreamsoftware.fitflextv.ui.core.IErrorMapper
import com.dreamsoftware.fitflextv.ui.core.SideEffect
import com.dreamsoftware.fitflextv.ui.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesTrainingsByUserUseCase: GetFavoritesTrainingsByUserUseCase,
    @FavoritesScreenErrorMapper private val errorMapper: IErrorMapper,
) : BaseViewModel<FavoritesUiState, FavoritesSideEffects>(), FavoritesScreenActionListener {

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
        updateState { it.copy(trainingProgramSelected = null) }
    }

    override fun onTrainingProgramRemoved(id: String) {
        updateState { it.copy(trainingProgramSelected = null) }
    }

    override fun onDismissRequest() {
        updateState { it.copy(trainingProgramSelected = null) }
    }

    private fun onGetFavoritesWorkoutsSuccessfully(trainingProgramList: List<ITrainingProgramBO>) {
        updateState { it.copy(favoritesTrainings = trainingProgramList) }
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

sealed interface FavoritesSideEffects: SideEffect