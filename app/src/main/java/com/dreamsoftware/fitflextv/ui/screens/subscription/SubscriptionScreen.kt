package com.dreamsoftware.fitflextv.ui.screens.subscription

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fitflextv.ui.core.components.CommonScreen

@Composable
fun SubscriptionScreen(
    viewModel: SubscriptionViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {
    CommonScreen(
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
