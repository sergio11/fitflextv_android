package com.dreamsoftware.fitflextv.ui.screens.player.audio.composable

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ButtonBorder
import androidx.tv.material3.IconButtonDefaults
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.ui.screens.player.composable.PlayerControlsIcon
import com.dreamsoftware.fitflextv.ui.theme.FitFlexTVTheme

@Composable
fun AudioPlayerControlsIcon(
    icon: Int,
    modifier: Modifier = Modifier,
    border: ButtonBorder = IconButtonDefaults.border(),
    buttonColor: Color = Color(0xFF37403D),
    size: Dp = 40.dp,
    onClick: () -> Unit
) {
    PlayerControlsIcon(
        modifier = modifier.size(size),
        icon = icon,
        border = border,
        buttonColor = buttonColor,
        onClick = onClick
    )
}

@Preview(device = Devices.TV_1080p)
@Composable
fun PreviewAudioPlayerControlsIcon() {
    FitFlexTVTheme {
        AudioPlayerControlsIcon(icon = R.drawable.play_icon) {

        }
    }
}