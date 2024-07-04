package com.dreamsoftware.fitflextv.ui.core.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.tv.material3.MaterialTheme

@Composable
fun CommonFullScreenImage(resourceId: Int) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            painter = painterResource(id = resourceId),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}