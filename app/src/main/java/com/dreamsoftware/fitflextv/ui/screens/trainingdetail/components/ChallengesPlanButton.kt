package com.dreamsoftware.fitflextv.ui.screens.trainingdetail.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.OutlinedButton
import androidx.tv.material3.Text

@Composable
fun ChallengesPlanButton(
    modifier: Modifier = Modifier,
    subtitle: String,
    iconId: Int,
    onClick: () -> Unit
) {
    with(MaterialTheme.colorScheme) {
        var isFocused by remember { mutableStateOf(false) }
        val transition = updateTransition(targetState = isFocused, label = null)
        val textColor by transition.animateColor(label = "textColor") {
            if (it) background else onSurfaceVariant
        }
        val iconColor by transition.animateColor(label = "iconColor") {
            if (it) background else onSurface
        }
        OutlinedButton(
            modifier = modifier
                .onFocusChanged {
                    isFocused = it.isFocused || it.hasFocus
                },
            onClick = onClick,
            colors = ButtonDefaults.colors(
                containerColor = Color.Transparent,
                focusedContainerColor = onBackground
            ),
            border = ButtonDefaults.border()
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = textColor
                )
                Icon(
                    painter = painterResource(iconId),
                    tint = iconColor,
                    contentDescription = null,
                )
            }
        }
    }
}