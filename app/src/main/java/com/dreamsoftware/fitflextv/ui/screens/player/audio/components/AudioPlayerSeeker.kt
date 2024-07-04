package com.dreamsoftware.fitflextv.ui.screens.player.audio.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dreamsoftware.fitflextv.ui.utils.toAudioTextDuration
import com.dreamsoftware.fitflextv.ui.utils.toAudioTextProgress
import kotlin.time.Duration

@Composable
internal fun AudioPlayerSeeker(
    onSeek: (Float) -> Unit,
    contentProgress: Duration,
    contentDuration: Duration,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        AudioPlayerControllerIndicator(
            progress = (contentProgress / contentDuration).toFloat(),
            onSeek = onSeek
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AudioPlayerDurationText(textDuration = contentProgress.toAudioTextProgress())
            AudioPlayerDurationText(textDuration = contentDuration.toAudioTextDuration())
        }
    }
}