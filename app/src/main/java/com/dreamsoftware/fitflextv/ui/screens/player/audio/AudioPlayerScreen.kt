package com.dreamsoftware.fitflextv.ui.screens.player.audio

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fudge.component.FudgeTvScreen

data class AudioPlayerScreenArgs(
    val id: String
)

@Composable
fun AudioPlayerScreen(
    viewModel: AudioPlayerViewModel = hiltViewModel(),
    args: AudioPlayerScreenArgs,
    onBackPressed: () -> Unit
) {
    FudgeTvScreen(
        viewModel = viewModel,
        onBackPressed = onBackPressed,
        onInitialUiState = { AudioPlayerUiState() },
        onInit = {
            fetchData(args.id)
        }
    ) { uiState ->
        AudioPlayerScreenContent(state = uiState)
    }
}

