package com.dreamsoftware.fitflextv.ui.screens.profiles.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import androidx.tv.material3.MaterialTheme

@Composable
fun CommonProfileGradientBox(content: @Composable BoxScope.() -> Unit) {
    with(MaterialTheme.colorScheme) {
        val gradient = Brush.radialGradient(
            0.33f to primary,
            0.66f to secondaryContainer,
            1.0f to primaryContainer,
            radius = 1500.0f,
            tileMode = TileMode.Repeated
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradient),
            content = content
        )
    }
}