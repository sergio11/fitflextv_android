package com.dreamsoftware.fitflextv.ui.screens.player.video

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fitflextv.domain.model.TrainingTypeEnum
import com.dreamsoftware.fitflextv.ui.core.components.CommonScreen

data class VideoPlayerScreenArgs(
    val id: String,
    val type: TrainingTypeEnum
)

@Composable
fun VideoPlayerScreen(
    viewModel: VideoPlayerViewModel = hiltViewModel(),
    args: VideoPlayerScreenArgs,
    onBackPressed: () -> Unit
) {
    CommonScreen(
        viewModel = viewModel,
        onBackPressed = onBackPressed,
        onInitialUiState = { VideoPlayerUiState() },
        onSideEffect = {

        },
        onInit = {
            with(args) {
                fetchData(id = id, type = type)
            }
        }
    ) { uiState ->
        VideoPlayerScreenContent(state = uiState)
    }
}