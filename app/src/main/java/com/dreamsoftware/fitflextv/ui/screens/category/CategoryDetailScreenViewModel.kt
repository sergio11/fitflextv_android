package com.dreamsoftware.fitflextv.ui.screens.category

import com.dreamsoftware.fitflextv.domain.model.CategoryBO
import com.dreamsoftware.fitflextv.domain.model.ChallengeBO
import com.dreamsoftware.fitflextv.domain.model.ITrainingProgramBO
import com.dreamsoftware.fitflextv.domain.model.SeriesBO
import com.dreamsoftware.fitflextv.domain.model.TrainingTypeEnum
import com.dreamsoftware.fitflextv.domain.model.WorkoutBO
import com.dreamsoftware.fitflextv.domain.usecase.GetCategoryByIdUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetTrainingsByCategoryUseCase
import com.dreamsoftware.fitflextv.ui.core.BaseViewModel
import com.dreamsoftware.fitflextv.ui.core.SideEffect
import com.dreamsoftware.fitflextv.ui.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryDetailScreenViewModel @Inject constructor(
    private val getTrainingsByCategoryUseCase: GetTrainingsByCategoryUseCase,
    private val getCategoryByIdUseCase: GetCategoryByIdUseCase
) : BaseViewModel<CategoryDetailUiState, CategoryDetailSideEffects>(), CategoryDetailActionListener {

    fun fetchData(id: String) {
        fetchTrainingsByCategory(id)
        fetchCategoryDetail(id)
    }

    override fun onGetDefaultState(): CategoryDetailUiState = CategoryDetailUiState()

    private fun fetchTrainingsByCategory(id: String) {
        executeUseCaseWithParams(
            useCase = getTrainingsByCategoryUseCase,
            params = GetTrainingsByCategoryUseCase.Params(id),
            onSuccess = ::onGetTrainingsByCategorySuccessfully
        )
    }

    private fun fetchCategoryDetail(id: String) {
        executeUseCaseWithParams(
            useCase = getCategoryByIdUseCase,
            params = GetCategoryByIdUseCase.Params(id),
            onSuccess = ::onGetCategoryDetailSuccessfully
        )
    }

    private fun onGetTrainingsByCategorySuccessfully(trainings: List<ITrainingProgramBO>) {
        updateState { it.copy(trainings = trainings) }
    }

    private fun onGetCategoryDetailSuccessfully(category: CategoryBO) {
        updateState { it.copy(category = category) }
    }

    override fun onTrainingProgramOpened(trainingProgram: ITrainingProgramBO) {
        launchSideEffect(
            CategoryDetailSideEffects.OpenTrainingProgramDetail(
                id = trainingProgram.id,
                type = when(trainingProgram) {
                    is WorkoutBO -> TrainingTypeEnum.WORK_OUT
                    is SeriesBO -> TrainingTypeEnum.SERIES
                    is ChallengeBO -> TrainingTypeEnum.CHALLENGES
                    else -> TrainingTypeEnum.ROUTINE
                }
            )
        )
    }
}

data class CategoryDetailUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val trainings: List<ITrainingProgramBO> = emptyList(),
    val category: CategoryBO? = null
): UiState<CategoryDetailUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): CategoryDetailUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface CategoryDetailSideEffects : SideEffect {
    data class OpenTrainingProgramDetail(val id: String, val type: TrainingTypeEnum): CategoryDetailSideEffects
}