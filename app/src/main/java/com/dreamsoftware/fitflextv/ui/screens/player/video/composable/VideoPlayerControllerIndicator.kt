package com.dreamsoftware.fitflextv.ui.screens.player.video.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.dreamsoftware.fitflextv.ui.screens.player.composable.PlayerControllerIndicator
import com.dreamsoftware.fitflextv.ui.theme.FitFlexTVTheme

@Composable
fun RowScope.VideoPlayerControllerIndicator(
    progress: Float,
    onSeek: (seekProgress: Float) -> Unit,
    state: VideoPlayerState,
    modifier: Modifier = Modifier,
) {
    var isSelected by remember { mutableStateOf(false) }

    LaunchedEffect(isSelected) {
        if (isSelected) {
            state.showControls(seconds = Int.MAX_VALUE)
        } else {
            state.showControls()
        }
    }

    PlayerControllerIndicator(
        progress = progress,
        onSeek = onSeek,
        isSelected = isSelected,
        onSelected = { isSelected = !isSelected },
        modifier = modifier.weight(1f)
    )
}


@Preview(device = Devices.TV_1080p)
@Composable
fun PreviewVideoPlayerControllerIndicator() {
    FitFlexTVTheme {
        Row {
            VideoPlayerControllerIndicator(
                progress = 0.1f,
                onSeek = {},
                state = rememberVideoPlayerState()
            )
        }
    }
}