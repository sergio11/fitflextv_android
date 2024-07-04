package com.dreamsoftware.fitflextv.ui.screens.player.video

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fitflextv.ui.core.components.CommonScreen

@Composable
fun VideoPlayerScreen(
    viewModel: VideoPlayerViewModel = hiltViewModel(),
) {
    CommonScreen(
        viewModel = viewModel,
        onInitialUiState = { VideoPlayerUiState() },
        onSideEffect = {

        },
        onInit = {
            fetchData("1")
        }
    ) { uiState ->
        VideoPlayerScreenContent(state = uiState)
    }
}