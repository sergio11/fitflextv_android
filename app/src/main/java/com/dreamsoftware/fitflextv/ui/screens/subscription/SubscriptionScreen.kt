package com.dreamsoftware.fitflextv.ui.screens.subscription

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fitflextv.ui.core.components.CommonScreen

@Composable
fun SubscriptionScreen(
    viewModel: SubscriptionViewModel = hiltViewModel()
) {
    CommonScreen(
        viewModel = viewModel,
        onInitialUiState = { SubscriptionUiState() }
    ) { uiState ->
        SubscriptionScreenContent(
            uiState = uiState,
            actionListener = viewModel
        )
    }
}
