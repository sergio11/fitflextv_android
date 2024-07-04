package com.dreamsoftware.fitflextv.ui.screens.moreoptions

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fitflextv.ui.core.components.CommonScreen

@Composable
fun MoreOptionsScreen(
    viewModel: MoreOptionsViewModel = hiltViewModel(),
    onBackPressed: () -> Unit,
    onStartClick: () -> Unit,
    onFavouriteClick: () -> Unit,
) {
    CommonScreen(
        viewModel = viewModel,
        onInitialUiState = { MoreOptionsUiState() },
        onSideEffect = {

        },
        onInit = {
            fetchData(1)
        }
    ) { uiState ->
        MoreOptionsScreenContent(
            state = uiState,
            onBackPressed = onBackPressed,
            onStartClick = onStartClick,
            onFavouriteClick = onFavouriteClick
        )
    }
}

