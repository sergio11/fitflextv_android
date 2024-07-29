package com.dreamsoftware.fitflextv.ui.screens.favorites

import androidx.compose.runtime.Composable
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
        onBackPressed = onBackPressed,
        onInitialUiState = { FavoritesUiState() },
        onSideEffect = {

        },
        onInit = {
            fetchData()
        }
    ) { uiState ->
        FavoritesScreenContent(
            uiState = uiState,
            actionListener = viewModel
        )
    }
}