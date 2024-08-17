package com.dreamsoftware.fitflextv.ui.screens.category

import com.dreamsoftware.fitflextv.domain.model.CategoryBO
import com.dreamsoftware.fitflextv.domain.model.ITrainingProgramBO
import com.dreamsoftware.fitflextv.domain.model.TrainingTypeEnum
import com.dreamsoftware.fitflextv.domain.usecase.GetCategoryByIdUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetTrainingsByCategoryUseCase
import com.dreamsoftware.fitflextv.ui.utils.toTrainingType
import com.dreamsoftware.fudge.core.FudgeTvViewModel
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryDetailScreenViewModel @Inject constructor(
    private val getTrainingsByCategoryUseCase: GetTrainingsByCategoryUseCase,
    private val getCategoryByIdUseCase: GetCategoryByIdUseCase
) : FudgeTvViewModel<CategoryDetailUiState, CategoryDetailSideEffects>(), CategoryDetailActionListener {

    fun fetchData(id: String) {
        fetchCategoryDetail(id)
    }

    override fun onGetDefaultState(): CategoryDetailUiState = CategoryDetailUiState()

    override fun onTrainingProgramOpened(trainingProgram: ITrainingProgramBO) {
        with(trainingProgram) {
            launchSideEffect(
                CategoryDetailSideEffects.OpenTrainingProgramDetail(
                    id = id,
                    type = toTrainingType()
                )
            )
        }
    }

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
        fetchTrainingsByCategory(category.id)
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