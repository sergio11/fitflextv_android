package com.dreamsoftware.fitflextv.ui.screens.player.video

import com.dreamsoftware.fitflextv.domain.model.ITrainingProgramBO
import com.dreamsoftware.fitflextv.domain.model.TrainingTypeEnum
import com.dreamsoftware.fitflextv.domain.usecase.GetChallengeByIdUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetRoutineByIdUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetSeriesByIdUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetWorkoutByIdUseCase
import com.dreamsoftware.fitflextv.ui.core.BaseViewModel
import com.dreamsoftware.fitflextv.ui.core.SideEffect
import com.dreamsoftware.fitflextv.ui.core.UiState
import com.dreamsoftware.fitflextv.ui.utils.EMPTY
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VideoPlayerViewModel @Inject constructor(
    private val getWorkoutByIdUseCase: GetWorkoutByIdUseCase,
    private val getRoutineByIdUseCase: GetRoutineByIdUseCase,
    private val getSeriesByIdUseCase: GetSeriesByIdUseCase,
    private val getChallengeByIdUseCase: GetChallengeByIdUseCase
) : BaseViewModel<VideoPlayerUiState, VideoPlayerSideEffects>() {

    override fun onGetDefaultState(): VideoPlayerUiState = VideoPlayerUiState()

    fun fetchData(id: String, type: TrainingTypeEnum) {
        when(type) {
            TrainingTypeEnum.WORK_OUT -> fetchGetWorkoutById(id)
            TrainingTypeEnum.SERIES -> fetchGetSeriesById(id)
            TrainingTypeEnum.CHALLENGES -> fetchGetChallengeById(id)
            TrainingTypeEnum.ROUTINE -> fetchGetRoutineById(id)
        }
    }

    private fun fetchGetWorkoutById(id: String) {
        executeUseCaseWithParams(
            useCase = getWorkoutByIdUseCase,
            params = GetWorkoutByIdUseCase.Params(id),
            onSuccess = ::onGetTrainingProgramByIdSuccessfully
        )
    }

    private fun fetchGetRoutineById(id: String) {
        executeUseCaseWithParams(
            useCase = getRoutineByIdUseCase,
            params = GetRoutineByIdUseCase.Params(id),
            onSuccess = ::onGetTrainingProgramByIdSuccessfully
        )
    }

    private fun fetchGetSeriesById(id: String) {
        executeUseCaseWithParams(
            useCase = getSeriesByIdUseCase,
            params = GetSeriesByIdUseCase.Params(id),
            onSuccess = ::onGetTrainingProgramByIdSuccessfully
        )
    }

    private fun fetchGetChallengeById(id: String) {
        executeUseCaseWithParams(
            useCase = getChallengeByIdUseCase,
            params = GetChallengeByIdUseCase.Params(id),
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