package com.dreamsoftware.fitflextv.ui.screens.player.video

import com.dreamsoftware.fitflextv.domain.model.WorkoutBO
import com.dreamsoftware.fitflextv.domain.usecase.GetWorkoutByIdUseCase
import com.dreamsoftware.fitflextv.ui.core.BaseViewModel
import com.dreamsoftware.fitflextv.ui.core.SideEffect
import com.dreamsoftware.fitflextv.ui.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VideoPlayerViewModel @Inject constructor(
    private val getWorkoutByIdUseCase: GetWorkoutByIdUseCase
) : BaseViewModel<VideoPlayerUiState, VideoPlayerSideEffects>() {

    override fun onGetDefaultState(): VideoPlayerUiState = VideoPlayerUiState()

    fun fetchData(workoutId: String) {
        executeUseCaseWithParams(
            useCase = getWorkoutByIdUseCase,
            params = GetWorkoutByIdUseCase.Params(workoutId),
            onSuccess = ::onGetWorkoutByIdSuccessfully
        )
    }

    private fun onGetWorkoutByIdSuccessfully(workoutBO: WorkoutBO) {
        updateState {
            with(workoutBO) {
                it.copy(
                    title = name,
                    instructor = instructorName,
                    videoUrl = videoUrl,
                    id = id,
                    subtitles = null,
                    subtitleUri = subtitleUri,
                )
            }
        }
    }
}

data class VideoPlayerUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val id: String = "",
    val videoUrl: String = "",
    val title: String = "",
    val instructor: String = "",
    val subtitles: String? = null,
    val subtitleUri: String? = null,
): UiState<VideoPlayerUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): VideoPlayerUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface VideoPlayerSideEffects: SideEffect