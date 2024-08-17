package com.dreamsoftware.fitflextv.ui.screens.dashboard

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import com.dreamsoftware.fitflextv.ui.navigation.Screen
import com.dreamsoftware.fudge.component.FudgeTvScreen

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = hiltViewModel(),
    navController: NavHostController,
    onNavigateToScreen: (Screen) -> Unit,
    currentDestination: NavDestination?
) {
    FudgeTvScreen(
        viewModel = viewModel,
        onInitialUiState = { DashboardUiState() },
        onSideEffect = {
            when(it) {
                is DashboardSideEffects.OpenScreen -> onNavigateToScreen(it.screen)
            }
        },
        onInit = {
            fetchData()
        }
    ) { uiState ->
        DashboardScreenContent(
            uiState = uiState,
            navController = navController,
            actionListener = viewModel,
            currentDestination = currentDestination
        )
    }
}