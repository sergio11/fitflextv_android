package com.dreamsoftware.fitflextv.ui.core.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource

@Composable
fun CommonImageRes(
    modifier: Modifier = Modifier,
    tint: Color? = null,
    @DrawableRes imageRes: Int
) {
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = null,
        modifier = modifier,
        colorFilter = tint?.let(ColorFilter::tint)
    )
}