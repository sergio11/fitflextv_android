package com.dreamsoftware.fitflextv.ui.screens.player.audio

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fitflextv.ui.core.components.CommonScreen

@Composable
fun AudioPlayerScreen(
    viewModel: AudioPlayerViewModel = hiltViewModel(),
) {
    CommonScreen(
        viewModel = viewModel,
        onInitialUiState = { AudioPlayerUiState() },
        onSideEffect = {

        },
        onInit = {
            fetchData("1")
        }
    ) { uiState ->
        AudioPlayerScreenContent(state = uiState)
    }
}

