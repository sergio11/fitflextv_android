package com.dreamsoftware.fitflextv.ui.screens.favorites

import com.dreamsoftware.fitflextv.domain.model.FavWorkout
import com.dreamsoftware.fitflextv.domain.usecase.GetFavoritesWorkoutsUseCase
import com.dreamsoftware.fitflextv.ui.core.BaseViewModel
import com.dreamsoftware.fitflextv.ui.core.SideEffect
import com.dreamsoftware.fitflextv.ui.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesWorkoutsUseCase: GetFavoritesWorkoutsUseCase
) : BaseViewModel<FavoritesUiState, FavoritesSideEffects>() {

    override fun onGetDefaultState(): FavoritesUiState = FavoritesUiState()

    fun fetchData() {
        executeUseCase(useCase = getFavoritesWorkoutsUseCase, onSuccess = ::onGetFavoritesWorkoutsSuccessfully)
    }

    fun onWorkoutSelect(favWorkout: FavWorkout) {
        updateState { it.copy(selectedWorkoutItem = favWorkout) }
    }

    fun onStartWorkout(id: String) {
        updateState { it.copy(selectedWorkoutItem = null) }
    }

    fun onRemoveWorkout(id: String) {
        updateState { it.copy(selectedWorkoutItem = null) }
    }

    fun onDismissRequest() {
        updateState { it.copy(selectedWorkoutItem = null) }
    }

    private fun onGetFavoritesWorkoutsSuccessfully(favoritesWorkouts: List<FavWorkout>) {
        updateState { it.copy(favoritesWorkouts = favoritesWorkouts) }
    }
}

data class FavoritesUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val favoritesWorkouts: List<FavWorkout> = emptyList(),
    val selectedWorkoutItem: FavWorkout? = null
): UiState<FavoritesUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): FavoritesUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface FavoritesSideEffects: SideEffect