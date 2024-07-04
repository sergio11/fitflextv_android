package com.dreamsoftware.fitflextv.ui.screens.favorites

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fitflextv.ui.core.components.CommonScreen

@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel = hiltViewModel(),
    onBackPressed: () -> Unit,
    onStartWorkout: (id: String) -> Unit
) {
    CommonScreen(
        viewModel = viewModel,
        onInitialUiState = { FavoritesUiState() },
        onSideEffect = {

        },
        onInit = {
            fetchData()
        }
    ) { uiState ->
        FavoritesScreenContent(
            modifier = Modifier,
            state = uiState,
            onWorkoutSelect = ::onWorkoutSelect,
            onStartWorkout = onStartWorkout,
            onRemoveWorkout = ::onRemoveWorkout,
            onBackPressed = onBackPressed
        )
    }
}