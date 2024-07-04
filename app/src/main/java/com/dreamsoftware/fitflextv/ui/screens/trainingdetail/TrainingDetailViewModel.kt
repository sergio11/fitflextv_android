package com.dreamsoftware.fitflextv.ui.screens.trainingdetail

import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.domain.model.ChallengeBO
import com.dreamsoftware.fitflextv.domain.model.RoutineBO
import com.dreamsoftware.fitflextv.domain.model.SeriesBO
import com.dreamsoftware.fitflextv.domain.model.WorkoutBO
import com.dreamsoftware.fitflextv.domain.usecase.GetChallengeByIdUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetRoutineByIdUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetTrainingSeriesByIdUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetWorkoutByIdUseCase
import com.dreamsoftware.fitflextv.ui.core.BaseViewModel
import com.dreamsoftware.fitflextv.ui.core.SideEffect
import com.dreamsoftware.fitflextv.ui.core.UiState
import com.dreamsoftware.fitflextv.ui.screens.trainingdetail.TrainingDetailUiState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrainingDetailViewModel @Inject constructor(
    private val getRoutineByIdUseCase: GetRoutineByIdUseCase,
    private val getWorkoutByIdUseCase: GetWorkoutByIdUseCase,
    private val getTrainingSeriesByIdUseCase: GetTrainingSeriesByIdUseCase,
    private val getChallengeByIdUseCase: GetChallengeByIdUseCase
) : BaseViewModel<TrainingDetailUiState, TrainingDetailSideEffects>() {
    val id: String = "1"

    override fun onGetDefaultState(): TrainingDetailUiState = TrainingDetailUiState()

    fun fetchData() {
        when (uiState.value.contentType) {
            ContentType.CHALLENGES -> fetchChallengeById(id)
            ContentType.SERIES -> fetchSeriesById(id)
            ContentType.WORK_OUT -> fetchWorkoutById(id)
            ContentType.ROUTINE -> fetchRoutineById(id)
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
                    subtitle = "$instructorName | ${workoutTypeEnum.value}",
                    title = name,
                    description = description,
                    itemsInfo = listOf(
                        TrainingInfoItem("$duration min", "Duration"),
                        TrainingInfoItem(intensityEnum.value, "Intensity")
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
                    subtitle = "$instructorName  |  ${workoutTypeEnum.value}",
                    title = name,
                    description = description,
                    itemsInfo = listOf(
                        TrainingInfoItem("$duration min", "Duration"),
                        TrainingInfoItem(intensityEnum.value, "Intensity")
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
                    subtitle = "$instructorName  |  ${intensityEnum.value}",
                    title = name,
                    description = description,
                    itemsInfo = listOf(
                        TrainingInfoItem(numberOfWeeks.toString(), "Week"),
                        TrainingInfoItem(numberOfClasses.toString(), "Classes"),
                        TrainingInfoItem(intensityEnum.value, "Intensity"),
                        TrainingInfoItem(minutesPerDay.toString(), "Minutes per day")
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
                    subtitle = "$instructorName  |  ${workoutTypeEnum.value}",
                    title = name,
                    description = description,
                    imageUrl = imageUrl,
                    tabs = weaklyPlans.map { weaklyPlan -> weaklyPlan.first },
                    itemsInfo = listOf(
                        TrainingInfoItem(numberOfDays.toString(), "Days"),
                        TrainingInfoItem(intensityEnum.value, "Intensity"),
                        TrainingInfoItem(minutesPerDay.toString(), "Minutes per day")
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
                                        typeText = workout.intensityEnum.level
                                    )
                                })
                        )
                    }
                )
            }
        }
    }
}

data class TrainingDetailUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val contentType: ContentType = ContentType.ROUTINE,
    val subtitle: String = "",
    val title: String = "",
    val description: String = "",
    val imageUrl: String = "",
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
        val info: String = "",
        val label: String = ""
    )

    enum class ContentType {
        CHALLENGES,
        SERIES,
        WORK_OUT,
        ROUTINE;

        fun getStartButtonID() = when (this) {
            CHALLENGES -> R.string.start_session
            SERIES -> R.string.start_program
            WORK_OUT -> R.string.start_workout
            ROUTINE -> R.string.start_routine
        }

        fun getSecondaryButtonID() = when (this) {
            CHALLENGES -> R.string.add_to_favorites
            SERIES -> R.string.recommend_schedule
            else -> R.string.set_up_daily_reminder
        }

        fun getSecondaryButtonIcon() = when (this) {
            CHALLENGES -> R.drawable.fav_icon
            SERIES -> R.drawable.message_icon
            else -> R.drawable.bell_icon
        }

        fun isSecondaryButtonVisible() = when (this) {
            WORK_OUT -> false
            else -> true
        }
    }
}

sealed class TrainingDetailPages {
    data object DetailDetails : TrainingDetailPages()
    data object DetailTabs : TrainingDetailPages()
}

sealed interface TrainingDetailSideEffects: SideEffect
