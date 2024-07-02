package com.dreamsoftware.fitflextv.ui.screens.more_options

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MoreOptionsScreen(
    viewModel: MoreOptionsViewModel = hiltViewModel(),
    onBackPressed: () -> Unit,
    onStartClick: () -> Unit,
    onFavouriteClick: () -> Unit,
) {
    val state by viewModel.uiState.collectAsState()
    MoreOptionsScreenContent(
        state = state,
        onBackPressed = onBackPressed,
        onStartClick = onStartClick,
        onFavouriteClick = onFavouriteClick
    )
}

