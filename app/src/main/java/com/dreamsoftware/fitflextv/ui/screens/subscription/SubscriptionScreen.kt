package com.dreamsoftware.fitflextv.ui.screens.subscription

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fudge.component.FudgeTvScreen

@Composable
fun SubscriptionScreen(
    viewModel: SubscriptionViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {
    FudgeTvScreen(
        viewModel = viewModel,
        onBackPressed = onBackPressed,
        onInitialUiState = { SubscriptionUiState() },
        onSideEffect = {
            when(it) {
                SubscriptionSideEffects.AddSubscriptionCompleted -> onBackPressed()
            }
        },
        onInit = {
            loadData()
        }
    ) { uiState ->
        SubscriptionScreenContent(
            uiState = uiState,
            actionListener = viewModel
        )
    }
}
