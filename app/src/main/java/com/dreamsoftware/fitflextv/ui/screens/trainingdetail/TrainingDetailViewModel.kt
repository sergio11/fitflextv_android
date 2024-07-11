package com.dreamsoftware.fitflextv.ui.screens.trainingdetail

import androidx.annotation.StringRes
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.domain.model.ChallengeBO
import com.dreamsoftware.fitflextv.domain.model.RoutineBO
import com.dreamsoftware.fitflextv.domain.model.SeriesBO
import com.dreamsoftware.fitflextv.domain.model.TrainingTypeEnum
import com.dreamsoftware.fitflextv.domain.model.WorkoutBO
import com.dreamsoftware.fitflextv.domain.usecase.GetChallengeByIdUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetRoutineByIdUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetTrainingSeriesByIdUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetWorkoutByIdUseCase
import com.dreamsoftware.fitflextv.ui.core.BaseViewModel
import com.dreamsoftware.fitflextv.ui.core.SideEffect
import com.dreamsoftware.fitflextv.ui.core.UiState
import com.dreamsoftware.fitflextv.ui.screens.trainingdetail.TrainingDetailUiState.*
import com.dreamsoftware.fitflextv.ui.utils.EMPTY
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrainingDetailViewModel @Inject constructor(
    private val getRoutineByIdUseCase: GetRoutineByIdUseCase,
    private val getWorkoutByIdUseCase: GetWorkoutByIdUseCase,
    private val getTrainingSeriesByIdUseCase: GetTrainingSeriesByIdUseCase,
    private val getChallengeByIdUseCase: GetChallengeByIdUseCase
) : BaseViewModel<TrainingDetailUiState, TrainingDetailSideEffects>(), TrainingDetailScreenActionListener {

    override fun onGetDefaultState(): TrainingDetailUiState = TrainingDetailUiState()

    fun fetchData(id: String, type: TrainingTypeEnum) {
        updateState { it.copy(trainingType = type) }
        when (type) {
            TrainingTypeEnum.CHALLENGES -> fetchChallengeById(id)
            TrainingTypeEnum.SERIES -> fetchSeriesById(id)
            TrainingTypeEnum.WORK_OUT -> fetchWorkoutById(id)
            TrainingTypeEnum.ROUTINE -> fetchRoutineById(id)
        }
    }

    private fun fetchRoutineById(id: String) {
        executeUseCaseWithParams(
            useCase = getRoutineByIdUseCase,
            params = GetRoutineByIdUseCase.Params(id),
            onSuccess = ::onGetRoutineByIdSuccessfully
        )
    }

    private fun fetchWorkoutById(id: String) {
        executeUseCaseWithParams(
            useCase = getWorkoutByIdUseCase,
            params = GetWorkoutByIdUseCase.Params(id),
            onSuccess = ::onGetWorkoutByIdSuccessfully
        )
    }

    private fun fetchSeriesById(id: String) {
        executeUseCaseWithParams(
            useCase = getTrainingSeriesByIdUseCase,
            params = GetTrainingSeriesByIdUseCase.Params(id),
            onSuccess = ::onGetTrainingSeriesIdSuccessfully
        )
    }

    private fun fetchChallengeById(id: String) {
        executeUseCaseWithParams(
            useCase = getChallengeByIdUseCase,
            params = GetChallengeByIdUseCase.Params(id),
            onSuccess = ::onGetChallengeByIdSuccessfully
        )
    }

    private fun onGetRoutineByIdSuccessfully(routineBO: RoutineBO) {
        updateState {
            with(routineBO) {
                it.copy(
                    subtitle = "$instructorName | ${workoutType.value}",
                    title = name,
                    description = description,
                    id = id,
                    itemsInfo = listOf(
                        TrainingInfoItem(info = "$duration min", labelRes = R.string.length),
                        TrainingInfoItem(info = intensity.value, labelRes = R.string.intensity)
                    ),
                    imageUrl = imageUrl
                )
            }
        }
    }

    private fun onGetWorkoutByIdSuccessfully(workoutBO: WorkoutBO) {
        updateState {
            with(workoutBO) {
                it.copy(
                    subtitle = "$instructorName  |  ${workoutType.value}",
                    title = name,
                    description = description,
                    id = id,
                    itemsInfo = listOf(
                        TrainingInfoItem(info = "$duration min", labelRes = R.string.length),
                        TrainingInfoItem(info = intensity.value, labelRes = R.string.intensity)
                    ),
                    imageUrl = imageUrl
                )
            }
        }
    }

    private fun onGetTrainingSeriesIdSuccessfully(seriesBO: SeriesBO) {
        updateState {
            with(seriesBO) {
                it.copy(
                    subtitle = "$instructorName  |  ${intensity.value}",
                    title = name,
                    description = description,
                    id = id,
                    itemsInfo = listOf(
                        TrainingInfoItem(info = numberOfWeeks.toString(), labelRes = R.string.week),
                        TrainingInfoItem(info = numberOfClasses.toString(), labelRes = R.string.classes),
                        TrainingInfoItem(info = intensity.value, labelRes = R.string.intensity),
                        TrainingInfoItem(info = duration, labelRes = R.string.classes)
                    ),
                    imageUrl = imageUrl
                )
            }
        }
    }

    private fun onGetChallengeByIdSuccessfully(challengeBO: ChallengeBO) {
        updateState {
            with(challengeBO) {
                it.copy(
                    subtitle = "$instructorName  |  ${workoutType.value}",
                    title = name,
                    description = description,
                    imageUrl = imageUrl,
                    id = id,
                    tabs = weaklyPlans.map { weaklyPlan -> weaklyPlan.first },
                    itemsInfo = listOf(
                        TrainingInfoItem(info = numberOfDays.toString(), labelRes = R.string.days),
                        TrainingInfoItem(info = intensity.value, labelRes = R.string.intensity),
                        TrainingInfoItem(info = duration, labelRes = R.string.minutes_per_day)
                    ),
                    weaklyPlans = weaklyPlans.map { weaklyPlan ->
                        mapOf(
                            Pair(
                                weaklyPlan.first,
                                weaklyPlan.second.map { workout ->
                                    ChallengeWorkoutItemUiState(
                                        id = workout.id,
                                        imageUrl = workout.imageUrl,
                                        title = workout.name,
                                        time = workout.duration,
                                        typeText = workout.intensity.level
                                    )
                                })
                        )
                    }
                )
            }
        }
    }

    override fun onTrainingProgramStarted() {
        launchSideEffect(TrainingDetailSideEffects.OpenTrainingProgram(id = uiState.value.id))
    }
}

data class TrainingDetailUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val trainingType: TrainingTypeEnum = TrainingTypeEnum.WORK_OUT,
    val subtitle: String = String.EMPTY,
    val title: String = String.EMPTY,
    val description: String = String.EMPTY,
    val imageUrl: String = String.EMPTY,
    val id: String = String.EMPTY,
    val itemsInfo: List<TrainingInfoItem> = listOf(),
    val tabs: List<String> = listOf(),
    val weaklyPlans: List<Map<String, List<ChallengeWorkoutItemUiState>>> = listOf(),
    val isFavorite: Boolean = false,
    val challengePages: List<TrainingDetailPages> = listOf(
        TrainingDetailPages.DetailDetails,
        TrainingDetailPages.DetailTabs
    )
): UiState<TrainingDetailUiState>(isLoading, errorMessage) {

    override fun copyState(isLoading: Boolean, errorMessage: String?): TrainingDetailUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)

    data class ChallengeWorkoutItemUiState(
        val id: String,
        val imageUrl: String,
        val title: String,
        val time: String,
        val typeText: String,
    )
    data class TrainingInfoItem(
        val info: String = String.EMPTY,
        @StringRes val labelRes: Int
    )
}

sealed class TrainingDetailPages {
    data object DetailDetails : TrainingDetailPages()
    data object DetailTabs : TrainingDetailPages()
}

sealed interface TrainingDetailSideEffects: SideEffect {
    data class OpenTrainingProgram(val id: String): TrainingDetailSideEffects
}
