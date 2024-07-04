package com.dreamsoftware.fitflextv.ui.screens.player.video.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dreamsoftware.fitflextv.ui.theme.FitFlexTVTheme
import com.dreamsoftware.fitflextv.ui.utils.toVideoTextDuration
import com.dreamsoftware.fitflextv.ui.utils.toVideoTextProgress
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

@Composable
internal fun VideoPlayerSeeker(
    state: VideoPlayerState,
    onSeek: (Float) -> Unit,
    contentProgress: Duration,
    contentDuration: Duration,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier, verticalAlignment = Alignment.CenterVertically
    ) {
        VideoPlayerControllerIndicator(
            progress = (contentProgress / contentDuration).toFloat(), onSeek = onSeek, state = state
        )
        VideoPlayerDurationText(
            modifier = Modifier.padding(
                horizontal = 12.dp
            ), textProgress = contentProgress.toVideoTextProgress(), textDuration = contentDuration.toVideoTextDuration()
        )
    }
}


@Preview
@Composable
fun PreviewVideoPlayerSeeker() {
    FitFlexTVTheme {
        VideoPlayerSeeker(
            state = rememberVideoPlayerState(),
            onSeek = {},
            contentProgress = (5L).milliseconds,
            contentDuration = (50L).milliseconds
        )
    }
}