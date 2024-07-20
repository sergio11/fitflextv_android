package com.dreamsoftware.fitflextv.ui.core.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme

@Composable
fun LoadingDialog(
    isShowingDialog: Boolean,
    @StringRes titleRes: Int,
    @StringRes descriptionRes: Int,
) {
    CommonDialog(
        isVisible = isShowingDialog,
        titleRes = titleRes,
        descriptionRes = descriptionRes
    ) {
        DialogContent()
    }
}

@Composable
private fun DialogContent() {
    with(MaterialTheme.colorScheme) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center),
                color = inverseOnSurface
            )
        }
    }
}