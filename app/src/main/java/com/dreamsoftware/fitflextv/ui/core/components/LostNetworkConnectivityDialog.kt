package com.dreamsoftware.fitflextv.ui.core.components

import androidx.compose.runtime.Composable
import com.dreamsoftware.fitflextv.R

@Composable
fun LostNetworkConnectivityDialog(
    isVisible: Boolean,
    onOpenSettings: () -> Unit,
    onRestartAppPressed: () -> Unit
) {
    CommonDialog(
        isVisible = isVisible,
        titleRes = R.string.generic_lost_network_connectivity_dialog_title,
        descriptionRes = R.string.generic_lost_network_connectivity_dialog_description,
        cancelRes = R.string.generic_lost_network_connectivity_dialog_open_settings_button_text,
        successRes = R.string.generic_lost_network_connectivity_dialog_restart_app_button_text,
        onCancelClicked = onOpenSettings,
        onAcceptClicked = onRestartAppPressed
    )
}