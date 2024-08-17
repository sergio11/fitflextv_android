package com.dreamsoftware.fitflextv.ui.core.components

import androidx.compose.runtime.Composable
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fudge.component.FudgeTvDialog

@Composable
fun ExitAppDialog(
    isVisible: Boolean,
    onDismissPressed: () -> Unit,
    onExitPressed: () -> Unit
) {
    FudgeTvDialog(
        isVisible = isVisible,
        mainLogoRes = R.drawable.main_logo,
        titleRes = R.string.onboarding_exit_app_dialog_title,
        descriptionRes = R.string.onboarding_exit_app_dialog_description,
        onCancelClicked = onDismissPressed,
        onAcceptClicked = onExitPressed
    )
}