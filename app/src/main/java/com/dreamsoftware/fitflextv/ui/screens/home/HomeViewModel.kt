package com.dreamsoftware.fitflextv.ui.screens.home

import com.dreamsoftware.fitflextv.domain.model.CategoryBO
import com.dreamsoftware.fitflextv.domain.model.SessionBO
import com.dreamsoftware.fitflextv.domain.model.TrainingBO
import com.dreamsoftware.fitflextv.domain.usecase.GetCategoriesUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetSessionsUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetTrainingsRecommendedUseCase
import com.dreamsoftware.fitflextv.ui.core.BaseViewModel
import com.dreamsoftware.fitflextv.ui.core.SideEffect
import com.dreamsoftware.fitflextv.ui.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSessionsUseCase: GetSessionsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getTrainingsRecommendedUseCase: GetTrainingsRecommendedUseCase
) : BaseViewModel<HomeUiState, HomeSideEffects>() {

    override fun onGetDefaultState(): HomeUiState = HomeUiState()

    fun fetchData() {
        fetchSessions()
        fetchCategories()
        fetchTrainingsRecommended()
    }

    private fun fetchSessions() {
        executeUseCase(useCase = getSessionsUseCase, onSuccess = ::onGetSessionsSuccessfully)
    }

    private fun fetchCategories() {
        executeUseCase(useCase = getCategoriesUseCase, onSuccess = ::onGetCategoriesSuccessfully)
    }

    private fun fetchTrainingsRecommended() {
        executeUseCase(useCase = getTrainingsRecommendedUseCase, onSuccess = ::onGetTrainingsRecommendedSuccessfully)
    }

    private fun onGetSessionsSuccessfully(sessions: List<SessionBO>) {
        updateState { it.copy(sessions = sessions) }
    }

    private fun onGetCategoriesSuccessfully(categories: List<CategoryBO>) {
        updateState { it.copy(categories = categories) }
    }

    private fun onGetTrainingsRecommendedSuccessfully(trainingsRecommended: List<TrainingBO>) {
        updateState { it.copy(recommended = trainingsRecommended) }
    }
}

data class HomeUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val categories: List<CategoryBO> = listOf(),
    val sessions: List<SessionBO> = emptyList(),
    val recommended: List<TrainingBO> = listOf()
): UiState<HomeUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): HomeUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface HomeSideEffects: SideEffect