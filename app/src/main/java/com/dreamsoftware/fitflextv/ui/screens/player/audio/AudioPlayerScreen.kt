package com.dreamsoftware.fitflextv.ui.screens.player.audio

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fitflextv.domain.model.TrainingTypeEnum
import com.dreamsoftware.fitflextv.ui.core.components.CommonScreen

data class AudioPlayerScreenArgs(
    val id: String
)

@Composable
fun AudioPlayerScreen(
    viewModel: AudioPlayerViewModel = hiltViewModel(),
    args: AudioPlayerScreenArgs,
    onBackPressed: () -> Unit
) {
    CommonScreen(
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

