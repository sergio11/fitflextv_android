package com.dreamsoftware.fitflextv.ui.core.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Border
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Surface
import com.dreamsoftware.fitflextv.R

private val DEFAULT_HORIZONTAL_PADDING = 32.dp
private val DEFAULT_AVATAR_SIZE = 100.dp
private const val DEFAULT_FOCUSED_SCALE = 1.4f
private val DEFAULT_AVATAR_BORDER_COLOR = Color.White

@Composable
fun ScalableAvatar(
    modifier: Modifier = Modifier,
    size: Dp = DEFAULT_AVATAR_SIZE,
    padding: Dp = DEFAULT_HORIZONTAL_PADDING,
    focusedScale: Float = DEFAULT_FOCUSED_SCALE,
    borderColor: Color = DEFAULT_AVATAR_BORDER_COLOR,
    editMode: Boolean = false,
    @DrawableRes avatarRes: Int?,
    onPressed: () -> Unit = {}
) {
    with(MaterialTheme.colorScheme) {
        Surface(
            onClick = onPressed,
            modifier = modifier
                .padding(horizontal = padding),
            border = ClickableSurfaceDefaults.border(
                border = Border(
                    border = BorderStroke(3.dp, borderColor),
                    shape = CircleShape,
                ),
                focusedBorder = Border(
                    border = BorderStroke(5.dp, borderColor),
                    shape = CircleShape,
                ),
            ),
            colors = ClickableSurfaceDefaults.colors(
                containerColor = surface,
                disabledContainerColor = surface,
                focusedContentColor = surface
            ),
            shape = ClickableSurfaceDefaults.shape(shape = CircleShape),
            scale = ClickableSurfaceDefaults.scale(focusedScale = focusedScale),
        ) {
            Box(
                modifier = Modifier
                    .size(size)
                    .clip(CircleShape)
                    .background(Color.Transparent)
            ) {
                Image(
                    painter = painterResource(id = avatarRes ?: R.drawable.person_ic),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                    colorFilter = if(avatarRes == null) {
                        ColorFilter.tint(primary)
                    } else {
                        null
                    }
                )
                if (editMode) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(primary.copy(0.6f))
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = "Edit",
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(4.dp),
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}