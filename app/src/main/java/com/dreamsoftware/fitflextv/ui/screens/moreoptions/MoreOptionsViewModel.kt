package com.dreamsoftware.fitflextv.ui.screens.moreoptions

import com.dreamsoftware.fitflextv.domain.model.ITrainingProgramBO
import com.dreamsoftware.fitflextv.domain.model.TrainingTypeEnum
import com.dreamsoftware.fitflextv.domain.usecase.AddFavoriteTrainingUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetTrainingByIdUseCase
import com.dreamsoftware.fitflextv.domain.usecase.RemoveFavoriteTrainingUseCase
import com.dreamsoftware.fitflextv.domain.usecase.VerifyTrainingInFavoritesUseCase
import com.dreamsoftware.fitflextv.ui.utils.toTrainingType
import com.dreamsoftware.fudge.core.FudgeViewModel
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoreOptionsViewModel @Inject constructor(
    private val getTrainingByIdUseCase: GetTrainingByIdUseCase,
    private val addFavoriteTrainingUseCase: AddFavoriteTrainingUseCase,
    private val removeFavoriteTrainingUseCase: RemoveFavoriteTrainingUseCase,
    private val verifyTrainingInFavoritesUseCase: VerifyTrainingInFavoritesUseCase
) : FudgeViewModel<MoreOptionsUiState, MoreOptionsSideEffects>(), MoreOptionsScreenActionListener {

    override fun onGetDefaultState(): MoreOptionsUiState = MoreOptionsUiState()

    fun fetchData(id: String, type: TrainingTypeEnum) {
        executeUseCaseWithParams(
            useCase = verifyTrainingInFavoritesUseCase,
            params = VerifyTrainingInFavoritesUseCase.Params(trainingId = id),
            onSuccess = ::onVerifyTrainingInFavoritesCompleted
        )
        executeUseCaseWithParams(
            useCase = getTrainingByIdUseCase,
            params = GetTrainingByIdUseCase.Params(id, type),
            onSuccess = ::onGetTrainingProgramByIdSuccessfully
        )
    }

    private fun onGetTrainingProgramByIdSuccessfully(trainingProgram: ITrainingProgramBO) {
        updateState { it.copy(trainingProgram = trainingProgram) }
    }

    override fun onBackPressed() {
        launchSideEffect(MoreOptionsSideEffects.ExitFromMoreDetail)
    }

    override fun onTrainingProgramOpened() {
        uiState.value.trainingProgram?.let {
            launchSideEffect(
                MoreOptionsSideEffects.PlayTrainingProgram(
                    id = it.id,
                    type = it.toTrainingType()
                )
            )
        }
    }

    override fun onFavouriteClicked() {
        with(uiState.value) {
            trainingProgram?.let {
                if (isFavorite) {
                    removeTrainingProgramFromFavorites(id = it.id)
                } else {
                    addTrainingProgramToFavorites(id = it.id, type = it.toTrainingType())
                }
            }
        }
    }

    override fun onOpenInstructorDetail() {
        uiState.value.trainingProgram?.let {
            launchSideEffect(
                MoreOptionsSideEffects.OpenInstructorDetail(id = it.instructorId)
            )
        }
    }

    override fun onPlayTrainingSong() {
        uiState.value.trainingProgram?.let {
            launchSideEffect(
                MoreOptionsSideEffects.PlayTrainingSong(songId = it.song)
            )
        }
    }

    private fun removeTrainingProgramFromFavorites(id: String) {
        executeUseCaseWithParams(
            useCase = removeFavoriteTrainingUseCase,
            params = RemoveFavoriteTrainingUseCase.Params(
                trainingId = id
            ),
            onSuccess = ::onChangeFavoriteTrainingCompleted
        )
    }

    private fun addTrainingProgramToFavorites(id: String, type: TrainingTypeEnum) {
        executeUseCaseWithParams(
            useCase = addFavoriteTrainingUseCase,
            params = AddFavoriteTrainingUseCase.Params(
                trainingId = id,
                trainingType = type
            ),
            onSuccess = ::onChangeFavoriteTrainingCompleted
        )
    }

    private fun onChangeFavoriteTrainingCompleted(isSuccess: Boolean) {
        if (isSuccess) {
            updateState { it.copy(isFavorite = !it.isFavorite) }
        }
    }

    private fun onVerifyTrainingInFavoritesCompleted(isFavorite: Boolean) {
        updateState { it.copy(isFavorite = isFavorite) }
    }
}

data class MoreOptionsUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val trainingProgram: ITrainingProgramBO? = null,
    val isFavorite: Boolean = false
) : UiState<MoreOptionsUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): MoreOptionsUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface MoreOptionsSideEffects : SideEffect {
    data class PlayTrainingProgram(val id: String, val type: TrainingTypeEnum) : MoreOptionsSideEffects
    data class PlayTrainingSong(val songId: String) : MoreOptionsSideEffects
    data class OpenInstructorDetail(val id: String): MoreOptionsSideEffects
    data object ExitFromMoreDetail: MoreOptionsSideEffects
}