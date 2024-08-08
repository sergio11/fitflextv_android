package com.dreamsoftware.fitflextv.ui.screens.training

import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.di.FavoritesScreenErrorMapper
import com.dreamsoftware.fitflextv.domain.model.ClassLanguageEnum
import com.dreamsoftware.fitflextv.domain.model.ITrainingProgramBO
import com.dreamsoftware.fitflextv.domain.model.IntensityEnum
import com.dreamsoftware.fitflextv.domain.model.SortTypeEnum
import com.dreamsoftware.fitflextv.domain.model.TrainingTypeEnum
import com.dreamsoftware.fitflextv.domain.model.VideoLengthEnum
import com.dreamsoftware.fitflextv.domain.model.WorkoutTypeEnum
import com.dreamsoftware.fitflextv.domain.usecase.GetInstructorsUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetTrainingsByTypeUseCase
import com.dreamsoftware.fitflextv.ui.core.BaseViewModel
import com.dreamsoftware.fitflextv.ui.core.IErrorMapper
import com.dreamsoftware.fitflextv.ui.core.SideEffect
import com.dreamsoftware.fitflextv.ui.core.UiState
import com.dreamsoftware.fitflextv.ui.utils.EMPTY
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrainingViewModel @Inject constructor(
    private val getInstructorsUseCase: GetInstructorsUseCase,
    private val getTrainingsByTypeUseCase: GetTrainingsByTypeUseCase,
    @FavoritesScreenErrorMapper private val errorMapper: IErrorMapper,
) : BaseViewModel<TrainingUiState, TrainingSideEffects>(), TrainingScreenActionListener {

    private var instructor: String = String.EMPTY
    private var videoLength: VideoLengthEnum = VideoLengthEnum.NOT_SET
    private var workoutType: WorkoutTypeEnum = WorkoutTypeEnum.NOT_SET
    private var intensity: IntensityEnum = IntensityEnum.NOT_SET
    private var classLanguage: ClassLanguageEnum = ClassLanguageEnum.NOT_SET
    private var sortType: SortTypeEnum = SortTypeEnum.NOT_SET

    override fun onGetDefaultState(): TrainingUiState = TrainingUiState(
        filterItems = listOf(
            TrainingFilterVO(
                type = FilterTypeEnum.VIDEO_LENGTH,
                icon = R.drawable.length_ic,
                title = R.string.length,
                description = VideoLengthEnum.NOT_SET.value,
                options = VideoLengthEnum.entries.map { it.value }
            ),
            TrainingFilterVO(
                type = FilterTypeEnum.CLASS_TYPE,
                icon = R.drawable.class_type_ic,
                title = R.string.class_type,
                description = WorkoutTypeEnum.NOT_SET.value,
                options = WorkoutTypeEnum.entries.map { it.value }
            ),
            TrainingFilterVO(
                type = FilterTypeEnum.CLASS_LANGUAGE,
                icon = R.drawable.language_ic,
                title = R.string.class_language,
                description = ClassLanguageEnum.NOT_SET.value,
                options = ClassLanguageEnum.entries.map { it.value }
            ),
            TrainingFilterVO(
                type = FilterTypeEnum.DIFFICULTY,
                icon = R.drawable.difficulty_ic,
                title = R.string.difficulty,
                description = IntensityEnum.NOT_SET.value,
                options = IntensityEnum.entries.map { it.value }
            ),
            TrainingFilterVO(
                type = FilterTypeEnum.INSTRUCTOR,
                icon = R.drawable.person_ic,
                title = R.string.instructor
            )
        )
    )

    fun fetchData() {
        fetchTrainings()
    }

    override fun onFilterClicked() {
        updateState { it.copy(isFilterExpended = !it.isFilterExpended) }
    }

    override fun onSortedClicked() {
        updateState { it.copy(isSortExpended = !it.isSortExpended) }
    }

    override fun onSortCleared() {
        sortType = SortTypeEnum.NOT_SET
        updateState { it.copy(selectedSortItem = 0, isSortExpended = false) }
        fetchTrainings()
    }

    override fun onDismissSortSideMenu() {
        updateState { it.copy(isSortExpended = false) }
    }

    override fun onDismissFilterSideMenu() {
        updateState { it.copy(isFilterExpended = false) }
    }

    override fun onFilterCleared() {
        videoLength = VideoLengthEnum.NOT_SET
        workoutType = WorkoutTypeEnum.NOT_SET
        intensity = IntensityEnum.NOT_SET
        classLanguage = ClassLanguageEnum.NOT_SET
        updateState {
            it.copy(
                isFilterExpended = false,
                filterItems = it.filterItems.map { item ->
                    item.copy(
                        selectedOption = 0,
                        description = when(item.type) {
                            FilterTypeEnum.VIDEO_LENGTH -> VideoLengthEnum.NOT_SET.value
                            FilterTypeEnum.CLASS_TYPE -> WorkoutTypeEnum.NOT_SET.value
                            FilterTypeEnum.DIFFICULTY -> IntensityEnum.NOT_SET.value
                            FilterTypeEnum.CLASS_LANGUAGE -> ClassLanguageEnum.NOT_SET.value
                            FilterTypeEnum.INSTRUCTOR -> String.EMPTY
                        }
                    )
                }
            )
        }
        fetchTrainings()
    }

    override fun onDismissFieldFilterSideMenu() {
        updateState { it.copy(isFieldFilterSelected = false) }
    }

    override fun onFilterFieldSelected(trainingFilter: TrainingFilterVO) {
        updateState {
            it.copy(
                selectedTrainingFilter = trainingFilter,
                isFieldFilterSelected = true
            )
        }
    }

    override fun onSelectedSortedItem(currentIndex: Int) {
        sortType = SortTypeEnum.entries[currentIndex]
        updateState { it.copy(selectedSortItem = currentIndex, isSortExpended = false) }
        fetchTrainings()
    }

    override fun onSelectedTrainingFilterOption(currentIndex: Int) {
        updateState { it.copy(isFieldFilterSelected = false) }
        uiState.value.selectedTrainingFilter?.let { filter ->
            when(filter.type) {
                FilterTypeEnum.VIDEO_LENGTH -> {
                    videoLength = VideoLengthEnum.entries[currentIndex]
                }
                FilterTypeEnum.CLASS_TYPE -> {
                    workoutType = WorkoutTypeEnum.entries[currentIndex]
                }
                FilterTypeEnum.DIFFICULTY -> {
                    intensity = IntensityEnum.entries[currentIndex]
                }
                FilterTypeEnum.CLASS_LANGUAGE -> {
                    classLanguage = ClassLanguageEnum.entries[currentIndex]
                }
                FilterTypeEnum.INSTRUCTOR -> String.EMPTY
            }
            updateState {
                it.copy(
                    filterItems = it.filterItems.map { item ->
                        if(item.type == filter.type) {
                            item.copy(
                                selectedOption = currentIndex,
                                description = when(filter.type) {
                                    FilterTypeEnum.VIDEO_LENGTH -> VideoLengthEnum.entries[currentIndex].value
                                    FilterTypeEnum.CLASS_TYPE -> WorkoutTypeEnum.entries[currentIndex].value
                                    FilterTypeEnum.DIFFICULTY -> IntensityEnum.entries[currentIndex].value
                                    FilterTypeEnum.CLASS_LANGUAGE -> ClassLanguageEnum.entries[currentIndex].value
                                    FilterTypeEnum.INSTRUCTOR -> String.EMPTY
                                }
                            )
                        } else {
                            item
                        }
                    },
                    selectedTrainingFilter = null
                )
            }
            fetchTrainings()
        }
    }

    override fun onChangeSelectedTab(index: Int) {
        updateState {
            it.copy(
                selectedTab = index,
                trainingPrograms = emptyList(),
                trainingTypeSelected = TrainingTypeEnum.entries[index]
            )
        }
        fetchTrainings()
    }

    override fun onChangeFocusTab(index: Int) {
        updateState { it.copy(focusTabIndex = index) }
    }

    override fun onItemClicked(id: String) {
        launchSideEffect(
            TrainingSideEffects.OpenTrainingProgramDetail(
                id = id,
                type = uiState.value.trainingTypeSelected
            )
        )
    }

    private fun fetchInstructors() {
        executeUseCase(useCase = getInstructorsUseCase, onSuccess = ::onGetInstructorsSuccessfully)
    }

    private fun fetchTrainings() {
        executeUseCaseWithParams(
            useCase = getTrainingsByTypeUseCase,
            params = GetTrainingsByTypeUseCase.Params(
                type = uiState.value.trainingTypeSelected,
                classLanguage = classLanguage,
                workoutType = workoutType,
                intensity = intensity,
                videoLength = videoLength,
                sortType = sortType
            ),
            onSuccess = ::onGetTrainingProgramsSuccessfully,
            onMapExceptionToState = ::onMapExceptionToState
        )
    }

    private fun onGetInstructorsSuccessfully(instructorList: List<String>) {
        instructor = instructorList.first()
        updateState {
            it.copy(
                instructorList = instructorList,
            )
        }
    }

    private fun onGetTrainingProgramsSuccessfully(trainingPrograms: List<ITrainingProgramBO>) {
        updateState { it.copy(trainingPrograms = trainingPrograms) }
        fetchInstructors()
    }

    private fun onMapExceptionToState(ex: Exception, uiState: TrainingUiState) =
        uiState.copy(
            isLoading = false,
            trainingPrograms = emptyList(),
            errorMessage = errorMapper.mapToMessage(ex)
        )
}

data class TrainingUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val isFilterExpended: Boolean = false,
    val isFieldFilterSelected: Boolean = false,
    val isSortExpended: Boolean = false,
    val instructorList: List<String> = emptyList(),
    val trainingPrograms: List<ITrainingProgramBO> = emptyList(),
    val filterItems: List<TrainingFilterVO> = emptyList(),
    val selectedSortItem: Int = 0,
    val selectedTrainingFilter: TrainingFilterVO? = null,
    val selectedTab: Int = 0,
    val tabsTitle: List<Int> = listOf(
        R.string.training_type_workout_name,
        R.string.training_type_series_name,
        R.string.training_type_challenges_name,
        R.string.training_type_routines_name,
    ),
    val trainingTypeSelected: TrainingTypeEnum = TrainingTypeEnum.WORK_OUT,
    val focusTabIndex: Int = 0,
) : UiState<TrainingUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): TrainingUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface TrainingSideEffects : SideEffect {
    data class OpenTrainingProgramDetail(val id: String, val type: TrainingTypeEnum) :
        TrainingSideEffects
}

data class TrainingFilterVO(
    val type: FilterTypeEnum,
    val icon: Int,
    val title: Int,
    val description: String = String.EMPTY,
    val selectedOption: Int = 0,
    val options: List<String> = emptyList()
)

enum class FilterTypeEnum {
    VIDEO_LENGTH, CLASS_TYPE, DIFFICULTY, CLASS_LANGUAGE, INSTRUCTOR
}