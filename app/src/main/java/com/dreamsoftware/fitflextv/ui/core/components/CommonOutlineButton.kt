package com.dreamsoftware.fitflextv.ui.core.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.OutlinedButton
import androidx.tv.material3.OutlinedButtonDefaults
import androidx.tv.material3.Text

@Composable
fun CommonOutlineButton(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = TextStyle(color = Color.White),
    onClick: () -> Unit,
    icon: Int? = null,
    iconPadding: Dp = 4.dp,
    iconTint: Color = Color.White
) {
    OutlinedButton(
        onClick = { onClick() },
        colors = OutlinedButtonDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier
    ) {
        icon?.let { painterResource(id = it) }
            ?.let {
                Icon(
                    painter = it,
                    contentDescription = "Icon",
                    modifier.padding(end = iconPadding),
                    tint = iconTint
                )
            }
        Text(text = text, style = textStyle)
    }
}

@Preview
@Composable
fun PreviewCustomOutlineButton() {
    CommonOutlineButton(text = "Add to favorites", onClick = {})
}