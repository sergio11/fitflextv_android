package com.dreamsoftware.fitflextv.ui.screens.trainingdetail

import androidx.annotation.StringRes
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.domain.model.ChallengeBO
import com.dreamsoftware.fitflextv.domain.model.ChallengeWeaklyPlansBO
import com.dreamsoftware.fitflextv.domain.model.ITrainingProgramBO
import com.dreamsoftware.fitflextv.domain.model.SeriesBO
import com.dreamsoftware.fitflextv.domain.model.TrainingTypeEnum
import com.dreamsoftware.fitflextv.domain.usecase.GetTrainingByIdUseCase
import com.dreamsoftware.fitflextv.ui.core.BaseViewModel
import com.dreamsoftware.fitflextv.ui.core.SideEffect
import com.dreamsoftware.fitflextv.ui.core.UiState
import com.dreamsoftware.fitflextv.ui.screens.trainingdetail.TrainingDetailUiState.ChallengeWorkoutItemUiState
import com.dreamsoftware.fitflextv.ui.screens.trainingdetail.TrainingDetailUiState.TrainingInfoItem
import com.dreamsoftware.fitflextv.ui.utils.EMPTY
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrainingDetailViewModel @Inject constructor(
    private val getTrainingByIdUseCase: GetTrainingByIdUseCase
) : BaseViewModel<TrainingDetailUiState, TrainingDetailSideEffects>(), TrainingDetailScreenActionListener {

    override fun onGetDefaultState(): TrainingDetailUiState = TrainingDetailUiState()

    fun fetchData(id: String, type: TrainingTypeEnum) {
        updateState { it.copy(trainingType = type) }
        executeUseCaseWithParams(
            useCase = getTrainingByIdUseCase,
            params = GetTrainingByIdUseCase.Params(id, type),
            onSuccess = ::onGetTrainingProgramByIdSuccessfully
        )
    }

    private fun onGetTrainingProgramByIdSuccessfully(trainingProgramBO: ITrainingProgramBO) {
        updateState {
            with(trainingProgramBO) {
                it.copy(
                    subtitle = "$instructorName | ${workoutType.value}",
                    title = name,
                    description = description,
                    id = id,
                    tabs = if(this is ChallengeBO) {
                        weaklyPlans.map(ChallengeWeaklyPlansBO::name)
                    } else {
                        emptyList()
                    },
                    weaklyPlans = if(this is ChallengeBO) {
                        weaklyPlans.map { weaklyPlan ->
                            mapOf(
                                Pair(
                                    weaklyPlan.name,
                                    weaklyPlan.workouts.map { workout ->
                                        with(workout) {
                                            ChallengeWorkoutItemUiState(
                                                id = id,
                                                imageUrl = imageUrl,
                                                title = name,
                                                time = duration,
                                                typeText = intensity.level
                                            )
                                        }
                                    })
                            )
                        }
                    } else {
                        emptyList()
                    },
                    itemsInfo = buildList {
                        add(TrainingInfoItem(info = "$duration min", labelRes = R.string.length))
                        add(TrainingInfoItem(info = intensity.value, labelRes = R.string.intensity))
                        when(this@with) {
                            is SeriesBO -> {
                                add(TrainingInfoItem(info = numberOfWeeks.toString(), labelRes = R.string.week))
                                add(TrainingInfoItem(info = numberOfClasses.toString(), labelRes = R.string.classes))
                            }
                            is ChallengeBO -> {
                                add(TrainingInfoItem(info = numberOfDays.toString(), labelRes = R.string.days))
                            }
                        }
                    },
                    imageUrl = imageUrl
                )
            }
        }
    }

    override fun onTrainingProgramStarted() {
        with(uiState.value) {
            launchSideEffect(TrainingDetailSideEffects.PlayingTrainingProgram(
                id = id,
                type = trainingType
            ))
        }
    }

    override fun onTrainingProgramMoreInfoRequested() {
        with(uiState.value) {
            launchSideEffect(TrainingDetailSideEffects.OpenMoreInfo(
                id = id,
                type = trainingType
            ))
        }
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
    data class PlayingTrainingProgram(val id: String, val type: TrainingTypeEnum): TrainingDetailSideEffects
    data class OpenMoreInfo(val id: String, val type: TrainingTypeEnum): TrainingDetailSideEffects
}
