package com.dreamsoftware.fitflextv.ui.core.components

import androidx.compose.runtime.Composable
import com.dreamsoftware.fitflextv.R

@Composable
fun ExitAppDialog(
    isVisible: Boolean,
    onDismissPressed: () -> Unit,
    onExitPressed: () -> Unit
) {
    CommonDialog(
        isVisible = isVisible,
        titleRes = R.string.onboarding_exit_app_dialog_title,
        descriptionRes = R.string.onboarding_exit_app_dialog_description,
        onCancelClicked = onDismissPressed,
        onAcceptClicked = onExitPressed
    )
}