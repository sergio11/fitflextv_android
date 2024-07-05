package com.dreamsoftware.fitflextv.ui.core.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Button
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.ui.theme.FitFlexTVTheme

@Composable
fun CommonMoreOptionsButton(
    modifier: Modifier = Modifier,
    @StringRes textRes: Int,
    @DrawableRes icon: Int,
    onClick: () -> Unit = {}
) {
    with(MaterialTheme.colorScheme) {
        Button(
            modifier = modifier.size(height = 50.dp, width = 292.dp),
            shape = ButtonDefaults.shape(shape = RoundedCornerShape(12.dp)),
            colors = ButtonDefaults.colors(
                focusedContainerColor = onSurface,
                containerColor = surfaceVariant.copy(0.4f),
                focusedContentColor = inverseOnSurface,
                contentColor = onSurface
            ),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 15.dp),
            scale = ButtonDefaults.scale(scale = 1f, focusedScale = 1.1f),
            onClick = onClick
        ) {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(id = icon),
                contentDescription = "button icon",
            )
            Spacer(modifier = Modifier.width(12.dp))
            CommonText(
                type = CommonTextTypeEnum.TITLE_MEDIUM,
                titleRes = textRes
            )
        }
    }
}

@Preview
@Composable
private fun MoreOptionsButtonPreview() {
    FitFlexTVTheme {
        CommonMoreOptionsButton(
            textRes = R.string.add_to_favorites,
            icon = R.drawable.ic_outline_favorite,
        )
    }
}