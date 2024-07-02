package com.dreamsoftware.fitflextv.ui.screens.favorites

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun FavoritesScreen(
    onBackPressed: () -> Unit,
    onStartWorkout: (id: String) -> Unit
) {

    val favoritesViewModel: FavoritesViewModel = hiltViewModel()
    val uiState by favoritesViewModel.uiState.collectAsStateWithLifecycle()
    val selectedItem by favoritesViewModel.selectedWorkout.collectAsStateWithLifecycle()

    when (val value = uiState) {
        is FavoritesScreenUiState.Ready -> {
            FavoritesScreenContent(
                modifier = Modifier,
                workoutsList = value.favoritesWorkouts,
                onWorkoutSelect = favoritesViewModel::onWorkoutSelect,
                onStartWorkout = onStartWorkout,
                onRemoveWorkout = favoritesViewModel::onRemoveWorkout,
                selectedItem = selectedItem,
                onBackPressed = onBackPressed
            )
        }

        is FavoritesScreenUiState.Loading -> {
            Loading(modifier = Modifier.fillMaxSize())
        }

        is FavoritesScreenUiState.Error -> {
            Error(modifier = Modifier.fillMaxSize())
        }
    }
}