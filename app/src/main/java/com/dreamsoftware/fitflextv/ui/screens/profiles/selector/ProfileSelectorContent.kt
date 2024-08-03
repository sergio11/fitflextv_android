package com.dreamsoftware.fitflextv.ui.screens.profiles.selector

import androidx.compose.runtime.Composable
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.ui.screens.profiles.components.CommonProfileScreenContent
import com.dreamsoftware.fitflextv.ui.screens.profiles.components.CommonProfileSelector

@Composable
fun ProfileSelectorContent(
    uiState: ProfileSelectorUiState,
    actionListener: ProfileSelectorScreenActionListener
) {
    with(uiState) {
        CommonProfileScreenContent(
            isLoading = uiState.isLoading,
            error = errorMessage,
            mainTitleRes = R.string.profile_selector_main_title,
            secondaryTitleRes = R.string.profile_selector_secondary_title,
            primaryOptionTextRes = R.string.profile_selector_add_profile_button_text,
            secondaryOptionTextRes = R.string.profile_selector_profile_management_button_text,
            onPrimaryOptionPressed = actionListener::onAddProfilePressed,
            onSecondaryOptionPressed = actionListener::onProfileManagementPressed,
            onErrorAccepted = actionListener::onErrorAccepted
        ) {
            CommonProfileSelector(
                profiles = uiState.profiles,
                onProfileSelected = actionListener::onProfileSelected
            )
        }
    }
}