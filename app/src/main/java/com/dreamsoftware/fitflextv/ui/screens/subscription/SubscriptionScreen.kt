package com.dreamsoftware.fitflextv.ui.screens.subscription

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SubscriptionScreen(
    viewModel: SubscriptionViewModel = hiltViewModel(),
    onSubscribeClick: () -> Unit,
    onRestorePurchasesClick: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    val instructorImage by viewModel.instructorImageState.collectAsState()
    val selectedSubscription by viewModel.selectedSubscriptionBOOption.collectAsState()

    SubscriptionScreenContent(
        state = state,
        instructorImage = instructorImage,
        selectedSubscriptionBO = selectedSubscription,
        updateSubscriptionOption = viewModel::updateSelectedSubscriptionOption,
        onSubscribeClick = onSubscribeClick,
        onRestorePurchasesClick = onRestorePurchasesClick,
    )
}
