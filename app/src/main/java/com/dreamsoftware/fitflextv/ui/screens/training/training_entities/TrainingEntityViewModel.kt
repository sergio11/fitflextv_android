package com.dreamsoftware.fitflextv.ui.screens.training.training_entities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dreamsoftware.fitflextv.data.repository.challenges.ChallengesRepository
import com.dreamsoftware.fitflextv.data.repository.routine.RoutineRepository
import com.dreamsoftware.fitflextv.data.repository.series.SeriesRepository
import com.dreamsoftware.fitflextv.data.repository.workout.WorkoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrainingEntityViewModel @Inject constructor(
    private val workoutRepository: WorkoutRepository,
    private val challengesRepository: ChallengesRepository,
    private val seriesRepository: SeriesRepository,
    private val routineRepository: RoutineRepository
) : ViewModel() {
    val id: String = "1"
    private val contentType: TrainingEntityUiState.ContentType =
        TrainingEntityUiState.ContentType.ROUTINE

    private val _state: MutableStateFlow<TrainingEntityUiState> by lazy {
        MutableStateFlow(
            TrainingEntityUiState()
        )
    }
    val state = _state.asStateFlow()

    init {
        _state.update { it.copy(contentType = contentType) }
        when (state.value.contentType) {
            TrainingEntityUiState.ContentType.CHALLENGES -> getChallengeById()
            TrainingEntityUiState.ContentType.SERIES -> getSeriesById()
            TrainingEntityUiState.ContentType.WORK_OUT -> getWorkoutById()
            TrainingEntityUiState.ContentType.ROUTINE -> getRoutineById()
        }

    }

    private fun getRoutineById() {
        viewModelScope.launch {
            routineRepository.getRoutineById(id).also { routine ->
                _state.update {
                    it.copy(
                        subtitle = "${routine.instructorName}  |  ${routine.workoutTypeEnum.value}",
                        title = routine.name,
                        description = routine.description,
                        itemsInfo = listOf(
                            TrainingEntityUiState.TrainingInfoItem(
                                "${routine.duration} min",
                                "Duration"
                            ),
                            TrainingEntityUiState.TrainingInfoItem(
                                routine.intensityEnum.value,
                                "Intensity"
                            )
                        ),
                        imageUrl = routine.imageUrl
                    )
                }
            }
        }
    }

    private fun getWorkoutById() {
        viewModelScope.launch {
            workoutRepository.getWorkoutById(id).also { workout ->
                _state.update {
                    it.copy(
                        subtitle = "${workout.instructorName}  |  ${workout.workoutTypeEnum.value}",
                        title = workout.name,
                        description = workout.description,
                        itemsInfo = listOf(
                            TrainingEntityUiState.TrainingInfoItem(
                                "${workout.duration} min",
                                "Duration"
                            ),
                            TrainingEntityUiState.TrainingInfoItem(
                                workout.intensityEnum.value,
                                "Intensity"
                            )
                        ),
                        imageUrl = workout.imageUrl
                    )
                }
            }
        }
    }

    private fun getSeriesById() {
        viewModelScope.launch {
            seriesRepository.getSeriesById(id).also { series ->
                _state.update {
                    it.copy(
                        subtitle = "${series.instructorName}  |  ${series.intensityEnum.value}",
                        title = series.name,
                        description = series.description,
                        itemsInfo = listOf(
                            TrainingEntityUiState.TrainingInfoItem(
                                series.numberOfWeeks.toString(),
                                "Week"
                            ),
                            TrainingEntityUiState.TrainingInfoItem(
                                series.numberOfClasses.toString(),
                                "Classes"
                            ),
                            TrainingEntityUiState.TrainingInfoItem(
                                series.intensityEnum.value,
                                "Intensity"
                            ),
                            TrainingEntityUiState.TrainingInfoItem(
                                series.minutesPerDay.toString(),
                                "Minutes per day"
                            )
                        ),
                        imageUrl = series.imageUrl
                    )
                }
            }
        }
    }

    private fun getChallengeById() {
        viewModelScope.launch {
            challengesRepository.getChallengeById(id).also { challenge ->
                _state.update {
                    it.copy(
                        subtitle = "${challenge.instructorName}  |  ${challenge.workoutTypeEnum.value}",
                        title = challenge.name,
                        description = challenge.description,
                        imageUrl = challenge.imageUrl,
                        tabs = challenge.weaklyPlans.map { weaklyPlan -> weaklyPlan.first },
                        itemsInfo = listOf(
                            TrainingEntityUiState.TrainingInfoItem(
                                challenge.numberOfDays.toString(),
                                "Days"
                            ),
                            TrainingEntityUiState.TrainingInfoItem(
                                challenge.intensityEnum.value,
                                "Intensity"
                            ),
                            TrainingEntityUiState.TrainingInfoItem(
                                challenge.minutesPerDay.toString(),
                                "Minutes per day"
                            )
                        ),
                        weaklyPlans = challenge.weaklyPlans.map { weaklyPlan ->
                            mapOf(
                                Pair(
                                    weaklyPlan.first,
                                    weaklyPlan.second.map { workout ->
                                        TrainingEntityUiState.ChallengeWorkoutItemUiState(
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
}