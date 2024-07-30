package com.dreamsoftware.fitflextv.ui.screens.training

import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.domain.model.ITrainingProgramBO
import com.dreamsoftware.fitflextv.domain.model.TrainingTypeEnum
import com.dreamsoftware.fitflextv.domain.usecase.GetInstructorsUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetTrainingsByTypeUseCase
import com.dreamsoftware.fitflextv.ui.core.BaseViewModel
import com.dreamsoftware.fitflextv.ui.core.SideEffect
import com.dreamsoftware.fitflextv.ui.core.UiState
import com.dreamsoftware.fitflextv.ui.utils.EMPTY
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrainingViewModel @Inject constructor(
    private val getInstructorsUseCase: GetInstructorsUseCase,
    private val getTrainingsByTypeUseCase: GetTrainingsByTypeUseCase
) : BaseViewModel<TrainingUiState, TrainingSideEffects>(), TrainingScreenActionListener {

    override fun onGetDefaultState(): TrainingUiState = TrainingUiState(
        filterItems = listOf(
            TrainingFilterVO(
                type = FilterTypeEnum.VIDEO_LENGTH,
                icon = R.drawable.length_ic,
                title = R.string.length,
                description = VideoLength.SHORT.value,
                options = VideoLength.entries.map { it.value }
            ),
            TrainingFilterVO(
                type = FilterTypeEnum.CLASS_TYPE,
                icon = R.drawable.class_type_ic,
                title = R.string.class_type,
                description = ClassType.STRENGTH.value,
                options = ClassType.entries.map { it.value }
            ),
            TrainingFilterVO(
                type = FilterTypeEnum.CLASS_LANGUAGE,
                icon = R.drawable.language_ic,
                title = R.string.class_language,
                description = ClassLanguage.ENGLISH.value,
                options = ClassLanguage.entries.map { it.value }
            ),
            TrainingFilterVO(
                type = FilterTypeEnum.DIFFICULTY,
                icon = R.drawable.difficulty_ic,
                title = R.string.difficulty,
                description = Difficulty.BEGINNER.value,
                options = Difficulty.entries.map { it.value }
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
        fetchInstructors()
    }

    override fun onFilterClicked() {
        updateState { it.copy(isFilterExpended = !it.isFilterExpended) }
    }

    override fun onSortedClicked() {
        updateState { it.copy(isSortExpended = !it.isSortExpended) }
    }

    override fun onDismissSortSideMenu() {
        updateState { it.copy(isSortExpended = false) }
    }

    override fun onDismissFilterSideMenu() {
        updateState { it.copy(isFilterExpended = false) }
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
        updateState { it.copy(selectedSortItem = currentIndex) }
    }

    override fun onSelectedTrainingFilterOption(currentIndex: Int) {
        updateState { it.copy(isFieldFilterSelected = false) }
        uiState.value.selectedTrainingFilter?.let { filter ->
            updateState {
                it.copy(
                    filterItems = it.filterItems.map { item ->
                        if(item.type == filter.type) {
                            item.copy(
                                selectedOption = currentIndex,
                                description = when(filter.type) {
                                    FilterTypeEnum.VIDEO_LENGTH -> VideoLength.entries[currentIndex].value
                                    FilterTypeEnum.CLASS_TYPE -> ClassType.entries[currentIndex].value
                                    FilterTypeEnum.DIFFICULTY -> Difficulty.entries[currentIndex].value
                                    FilterTypeEnum.CLASS_LANGUAGE -> ClassLanguage.entries[currentIndex].value
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
            params = GetTrainingsByTypeUseCase.Params(uiState.value.trainingTypeSelected),
            onSuccess = ::onGetTrainingProgramsSuccessfully
        )
    }

    private fun onGetInstructorsSuccessfully(instructorList: List<String>) {
        updateState {
            it.copy(
                instructorList = instructorList,
                instructor = instructorList.first()
            )
        }
    }

    private fun onGetTrainingProgramsSuccessfully(trainingPrograms: List<ITrainingProgramBO>) {
        updateState { it.copy(trainingPrograms = trainingPrograms) }
    }
}

data class TrainingUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val isFilterExpended: Boolean = false,
    val isFieldFilterSelected: Boolean = false,
    val isSortExpended: Boolean = false,
    val instructor: String = String.EMPTY,
    val videoLength: VideoLength = VideoLength.SHORT,
    val classType: ClassType = ClassType.STRENGTH,
    val difficulty: Difficulty = Difficulty.BEGINNER,
    val classLanguage: ClassLanguage = ClassLanguage.ENGLISH,
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

enum class VideoLength(val value: String) {
    SHORT("15-30 min"), MEDIUM("30-45 min"), LONG("45-60 min")
}

enum class ClassType(val value: String) {
    STRENGTH("Strength")
}

enum class Difficulty(val value: String) {
    BEGINNER("Beginner"), INTERMEDIATE("Intermediate"), ADVANCED("Advanced")
}

enum class ClassLanguage(val value: String) {
    ENGLISH("English"),
    SPANISH("Spanish"),
    FRENCH("French"),
    GERMAN("German"),
    ITALIAN("Italian"),
    RUSSIAN("Russian"),
    JAPANESE("Japanese"),
    CHINESE("Chinese"),
    KOREAN("Korean")
}

enum class SortItem {
    Newest, Relevance, Oldest
}