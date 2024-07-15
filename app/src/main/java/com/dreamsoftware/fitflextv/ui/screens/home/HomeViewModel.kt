package com.dreamsoftware.fitflextv.ui.screens.home

import com.dreamsoftware.fitflextv.domain.model.CategoryBO
import com.dreamsoftware.fitflextv.domain.model.ITrainingProgramBO
import com.dreamsoftware.fitflextv.domain.model.TrainingTypeEnum
import com.dreamsoftware.fitflextv.domain.usecase.GetCategoriesUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetFeaturedTrainingsUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetTrainingsRecommendedUseCase
import com.dreamsoftware.fitflextv.ui.core.BaseViewModel
import com.dreamsoftware.fitflextv.ui.core.SideEffect
import com.dreamsoftware.fitflextv.ui.core.UiState
import com.dreamsoftware.fitflextv.ui.utils.toTrainingType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFeaturedTrainingsUseCase: GetFeaturedTrainingsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getTrainingsRecommendedUseCase: GetTrainingsRecommendedUseCase
) : BaseViewModel<HomeUiState, HomeSideEffects>(), HomeScreenActionListener {

    override fun onGetDefaultState(): HomeUiState = HomeUiState()

    fun fetchData() {
        fetchFeaturedTrainings()
        fetchCategories()
        fetchTrainingsRecommended()
    }

    private fun fetchFeaturedTrainings() {
        executeUseCase(useCase = getFeaturedTrainingsUseCase, onSuccess = ::onGetFeaturedTrainingsSuccessfully)
    }

    private fun fetchCategories() {
        executeUseCase(useCase = getCategoriesUseCase, onSuccess = ::onGetCategoriesSuccessfully)
    }

    private fun fetchTrainingsRecommended() {
        executeUseCase(useCase = getTrainingsRecommendedUseCase, onSuccess = ::onGetTrainingsRecommendedSuccessfully)
    }

    private fun onGetFeaturedTrainingsSuccessfully(trainings: List<ITrainingProgramBO>) {
        updateState { it.copy(featuredTrainings = trainings) }
    }

    private fun onGetCategoriesSuccessfully(categories: List<CategoryBO>) {
        updateState { it.copy(categories = categories) }
    }

    private fun onGetTrainingsRecommendedSuccessfully(trainingsRecommended: List<ITrainingProgramBO>) {
        updateState { it.copy(recommended = trainingsRecommended) }
    }

    override fun onOpenTrainingProgram(trainingProgram: ITrainingProgramBO) {
        with(trainingProgram) {
            launchSideEffect(HomeSideEffects.OpenTrainingProgram(
                id = id,
                type = toTrainingType()
            ))
        }
    }

    override fun onCategorySelected(categoryId: String) {
        launchSideEffect(HomeSideEffects.OpenTrainingCategory(categoryId))
    }
}

data class HomeUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val categories: List<CategoryBO> = listOf(),
    val featuredTrainings: List<ITrainingProgramBO> = emptyList(),
    val recommended: List<ITrainingProgramBO> = listOf()
): UiState<HomeUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): HomeUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface HomeSideEffects: SideEffect {
    data class OpenTrainingProgram(val id: String, val type: TrainingTypeEnum): HomeSideEffects
    data class OpenTrainingCategory(val categoryId: String): HomeSideEffects
}