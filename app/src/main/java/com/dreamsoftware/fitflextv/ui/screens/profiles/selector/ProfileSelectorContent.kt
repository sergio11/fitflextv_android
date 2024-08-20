package com.dreamsoftware.fitflextv.ui.screens.profiles.selector

import androidx.compose.runtime.Composable
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fudge.component.profiles.FudgeTvProfileScreenContent
import com.dreamsoftware.fudge.component.profiles.FudgeTvProfileSelector

@Composable
internal fun ProfileSelectorContent(
    uiState: ProfileSelectorUiState,
    actionListener: ProfileSelectorScreenActionListener
) {
    with(uiState) {
        FudgeTvProfileScreenContent(
            isLoading = uiState.isLoading,
            error = errorMessage,
            mainLogoRes = R.drawable.main_logo,
            mainLogoInverseRes = R.drawable.main_logo_inverse,
            loadingTitleRes = R.string.generic_progress_dialog_title,
            loadingDescriptionRes = R.string.generic_progress_dialog_description,
            mainTitleRes = R.string.profile_selector_main_title,
            secondaryTitleRes = R.string.profile_selector_secondary_title,
            primaryOptionTextRes = R.string.profile_selector_add_profile_button_text,
            secondaryOptionTextRes = R.string.profile_selector_profile_management_button_text,
            onPrimaryOptionPressed = actionListener::onAddProfilePressed,
            onSecondaryOptionPressed = actionListener::onProfileManagementPressed,
            onErrorAccepted = actionListener::onErrorMessageCleared
        ) {
            FudgeTvProfileSelector(
                profiles = uiState.profiles,
                onProfileSelected = actionListener::onProfileSelected
            )
        }
    }
}