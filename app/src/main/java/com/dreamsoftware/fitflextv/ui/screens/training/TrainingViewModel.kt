package com.dreamsoftware.fitflextv.ui.screens.training

import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.domain.model.WorkoutBO
import com.dreamsoftware.fitflextv.domain.usecase.GetInstructorsUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetWorkoutsUseCase
import com.dreamsoftware.fitflextv.ui.core.BaseViewModel
import com.dreamsoftware.fitflextv.ui.core.SideEffect
import com.dreamsoftware.fitflextv.ui.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrainingViewModel @Inject constructor(
    private val getInstructorsUseCase: GetInstructorsUseCase,
    private val getWorkoutsUseCase: GetWorkoutsUseCase
) : BaseViewModel<TrainingUiState, TrainingSideEffects>() {

    override fun onGetDefaultState(): TrainingUiState = with(FilterSideMenuVO()) {
        TrainingUiState(
            filterSideMenuVO = this,
            filterItems = listOf(
                TrainingFilterData(icon = R.drawable.length_ic, title = R.string.length, description = videoLength.value),
                TrainingFilterData(icon = R.drawable.person_ic, title = R.string.instructor, description = instructor),
                TrainingFilterData(icon = R.drawable.class_type_ic, title = R.string.class_type, description = classType.name),
                TrainingFilterData(icon = R.drawable.language_ic, title = R.string.class_language, description = classLanguage.name),
                TrainingFilterData(icon = R.drawable.difficulty_ic, title = R.string.difficulty, description = difficulty.name),
                TrainingFilterData(icon = R.drawable.subtitle_ic, title = R.string.subtitles, description = subtitles.name)
            )
        )
    }

    fun fetchData() {
        fetchWorkout()
        fetchInstructors()
    }

    private fun fetchInstructors() {
        executeUseCase(useCase = getInstructorsUseCase, onSuccess = ::onGetInstructorsSuccessfully)
    }

    private fun fetchWorkout() {
        executeUseCase(useCase = getWorkoutsUseCase, onSuccess = ::onGetWorkoutsSuccessfully)
    }

    private fun onGetInstructorsSuccessfully(instructorList: List<String>) {
        updateState {
            it.copy(
                instructorList = instructorList,
                filterSideMenuVO = it.filterSideMenuVO.copy(instructor = instructorList.first())
            )
        }
    }

    private fun onGetWorkoutsSuccessfully(workoutList: List<WorkoutBO>) {
        updateState { it.copy(workoutList = workoutList) }
    }

    fun onFilterClicked() {
        updateState { it.copy(isFilterExpended = !it.isFilterExpended) }
    }

    fun onSortedClicked() {
        updateState { it.copy(isSortExpended = !it.isSortExpended) }
    }

    fun onDismissSideMenu() {
        updateState { it.copy(isFilterExpended = false, isSortExpended = false) }
    }

    fun onSelectedSortedItem(sortItemIndex: Int) {
        updateState { it.copy(selectedSortItem = sortItemIndex) }
    }

    fun onChangeSelectedTab(index: Int) {
        updateState { it.copy(selectedTab = index) }
    }

    fun onChangeFocusTab(index: Int) {
        updateState { it.copy(focusTabIndex = index) }
    }
}

data class TrainingUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val isFilterExpended: Boolean = false,
    val isSortExpended: Boolean = false,
    val filterSideMenuVO: FilterSideMenuVO = FilterSideMenuVO(),
    val instructorList: List<String> = emptyList(),
    val workoutList: List<WorkoutBO> = emptyList(),
    val filterItems: List<TrainingFilterData> = emptyList(),
    val selectedSortItem: Int = 0,
    val selectedTab: Int = 0,
    val focusTabIndex: Int = 0,
) : UiState<TrainingUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): TrainingUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface TrainingSideEffects : SideEffect

data class TrainingFilterData(
    val icon: Int,
    val title: Int,
    val description: String,
)

data class FilterSideMenuVO(
    val videoLength: VideoLength = VideoLength.SHORT,
    val instructor: String = "",
    val classType: ClassType = ClassType.Strength,
    val difficulty: Difficulty = Difficulty.Beginner,
    val classLanguage: ClassLanguage = ClassLanguage.English,
    val subtitles: Subtitles = Subtitles.Available,
) {
    enum class VideoLength(val value: String) {
        SHORT("15-30"), MEDIUM("30-45"), LONG("45-60")
    }

    enum class ClassType {
        Strength
    }

    enum class Difficulty {
        Beginner, Intermediate, Advanced
    }

    enum class ClassLanguage {
        English, Spanish, French, German, Italian, Russian, Japanese, Chinese, Korean, Arabic
    }

    enum class Subtitles {
        Available, NotAvailable
    }
}

enum class SortItem {
    Newest, Relevance, Oldest
}