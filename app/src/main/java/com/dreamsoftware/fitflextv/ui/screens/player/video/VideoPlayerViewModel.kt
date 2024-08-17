package com.dreamsoftware.fitflextv.ui.screens.player.video

import com.dreamsoftware.fitflextv.domain.model.ITrainingProgramBO
import com.dreamsoftware.fitflextv.domain.model.TrainingTypeEnum
import com.dreamsoftware.fitflextv.domain.usecase.GetTrainingByIdUseCase
import com.dreamsoftware.fitflextv.ui.utils.EMPTY
import com.dreamsoftware.fudge.core.FudgeTvViewModel
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VideoPlayerViewModel @Inject constructor(
    private val getTrainingByIdUseCase: GetTrainingByIdUseCase
) : FudgeTvViewModel<VideoPlayerUiState, VideoPlayerSideEffects>() {

    override fun onGetDefaultState(): VideoPlayerUiState = VideoPlayerUiState()

    fun fetchData(id: String, type: TrainingTypeEnum) {
        executeUseCaseWithParams(
            useCase = getTrainingByIdUseCase,
            params = GetTrainingByIdUseCase.Params(id, type),
            onSuccess = ::onGetTrainingProgramByIdSuccessfully
        )
    }

    private fun onGetTrainingProgramByIdSuccessfully(trainingProgram: ITrainingProgramBO) {
        updateState {
            with(trainingProgram) {
                it.copy(
                    title = name,
                    instructor = instructorName,
                    videoUrl = videoUrl,
                    id = id
                )
            }
        }
    }
}

data class VideoPlayerUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val id: String = String.EMPTY,
    val videoUrl: String = String.EMPTY,
    val title: String = String.EMPTY,
    val instructor: String = String.EMPTY
): UiState<VideoPlayerUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): VideoPlayerUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface VideoPlayerSideEffects: SideEffect