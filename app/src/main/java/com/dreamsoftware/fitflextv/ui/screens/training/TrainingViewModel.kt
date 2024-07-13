package com.dreamsoftware.fitflextv.ui.screens.training

import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.domain.model.ITrainingProgramBO
import com.dreamsoftware.fitflextv.domain.model.TrainingTypeEnum
import com.dreamsoftware.fitflextv.domain.usecase.GetInstructorsUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetTrainingsByTypeUseCase
import com.dreamsoftware.fitflextv.ui.core.BaseViewModel
import com.dreamsoftware.fitflextv.ui.core.SideEffect
import com.dreamsoftware.fitflextv.ui.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrainingViewModel @Inject constructor(
    private val getInstructorsUseCase: GetInstructorsUseCase,
    private val getTrainingsByTypeUseCase: GetTrainingsByTypeUseCase
) : BaseViewModel<TrainingUiState, TrainingSideEffects>(), TrainingScreenActionListener {

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
        fetchTrainings()
        fetchInstructors()
    }

    override fun onFilterClicked() {
        updateState { it.copy(isFilterExpended = !it.isFilterExpended) }
    }

    override fun onSortedClicked() {
        updateState { it.copy(isSortExpended = !it.isSortExpended) }
    }

    override fun onDismissSideMenu() {
        updateState { it.copy(isFilterExpended = false, isSortExpended = false) }
    }

    override fun onSelectedSortedItem(currentIndex: Int) {
        updateState { it.copy(selectedSortItem = currentIndex) }
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
        launchSideEffect(TrainingSideEffects.OpenTrainingProgramDetail(
            id = id,
            type = uiState.value.trainingTypeSelected
        ))
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
                filterSideMenuVO = it.filterSideMenuVO.copy(instructor = instructorList.first())
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
    val isSortExpended: Boolean = false,
    val filterSideMenuVO: FilterSideMenuVO = FilterSideMenuVO(),
    val instructorList: List<String> = emptyList(),
    val trainingPrograms: List<ITrainingProgramBO> = emptyList(),
    val filterItems: List<TrainingFilterData> = emptyList(),
    val selectedSortItem: Int = 0,
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

    data class OpenTrainingProgramDetail(val id: String, val type: TrainingTypeEnum): TrainingSideEffects
}

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