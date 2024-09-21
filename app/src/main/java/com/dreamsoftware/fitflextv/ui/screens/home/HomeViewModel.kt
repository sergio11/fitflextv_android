package com.dreamsoftware.fitflextv.ui.screens.home

import androidx.lifecycle.SavedStateHandle
import com.dreamsoftware.fitflextv.domain.model.CategoryBO
import com.dreamsoftware.fitflextv.domain.model.ITrainingProgramBO
import com.dreamsoftware.fitflextv.domain.model.TrainingTypeEnum
import com.dreamsoftware.fitflextv.domain.usecase.GetCategoriesUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetFeaturedTrainingsUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetTrainingsRecommendedUseCase
import com.dreamsoftware.fitflextv.domain.usecase.HasActiveSubscriptionUseCase
import com.dreamsoftware.fitflextv.ui.utils.toTrainingType
import com.dreamsoftware.fudge.core.FudgeTvViewModel
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFeaturedTrainingsUseCase: GetFeaturedTrainingsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getTrainingsRecommendedUseCase: GetTrainingsRecommendedUseCase,
    private val hasActiveSubscriptionUseCase: HasActiveSubscriptionUseCase,
    private val savedStateHandle: SavedStateHandle
) : FudgeTvViewModel<HomeUiState, HomeSideEffects>(), HomeScreenActionListener {

    companion object {
        private const val KEY_SUBSCRIPTION_VERIFIED = "subscription_verified"
    }
    private var subscriptionAlreadyVerified: Boolean
        get() = savedStateHandle[KEY_SUBSCRIPTION_VERIFIED] ?: false
        set(value) {
            savedStateHandle[KEY_SUBSCRIPTION_VERIFIED] = value
        }

    override fun onGetDefaultState(): HomeUiState = HomeUiState()

    fun fetchData() {
        fetchFeaturedTrainings()
        fetchCategories()
        fetchTrainingsRecommended()
        if(!subscriptionAlreadyVerified) {
            verifyHasActiveSubscription()
        }
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

    private fun verifyHasActiveSubscription() {
        executeUseCase(useCase = hasActiveSubscriptionUseCase, onSuccess = ::onVerifyHasActiveSubscriptionCompleted)
    }

    private fun onGetFeaturedTrainingsSuccessfully(trainings: List<ITrainingProgramBO>) {
        updateState { it.copy(featuredTrainings = trainings) }
    }

    private fun onVerifyHasActiveSubscriptionCompleted(hasActiveSubscription: Boolean){
        if(!hasActiveSubscription) {
            launchSideEffect(HomeSideEffects.NoActivePremiumSubscription)
        }
        subscriptionAlreadyVerified = true
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
    data object NoActivePremiumSubscription: HomeSideEffects
}