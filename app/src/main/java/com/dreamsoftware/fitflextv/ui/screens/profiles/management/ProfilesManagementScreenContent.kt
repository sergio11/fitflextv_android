package com.dreamsoftware.fitflextv.ui.screens.profiles.management

import androidx.compose.runtime.Composable
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fudge.component.profiles.FudgeTvProfileScreenContent
import com.dreamsoftware.fudge.component.profiles.FudgeTvProfileSelector

@Composable
fun ProfilesManagementScreenContent(
    uiState: ProfilesManagementUiState,
    onCompletePressed: () -> Unit,
    onProfileSelected: (profileId: String) -> Unit,
    onErrorAccepted: () -> Unit
) {
    with(uiState) {
        FudgeTvProfileScreenContent(
            isLoading = isLoading,
            error = errorMessage,
            mainLogoRes = R.drawable.main_logo,
            mainLogoInverseRes = R.drawable.main_logo_inverse,
            loadingTitleRes = R.string.generic_progress_dialog_title,
            loadingDescriptionRes = R.string.generic_progress_dialog_description,
            mainTitleRes = R.string.profiles_management_main_title,
            secondaryTitleRes = R.string.profiles_management_main_description,
            primaryOptionTextRes = R.string.profiles_management_form_confirm_button_text,
            onPrimaryOptionPressed = onCompletePressed,
            onErrorAccepted = onErrorAccepted
        ) {
            FudgeTvProfileSelector(
                profiles = uiState.profiles,
                editMode = true,
                onProfileSelected = onProfileSelected
            )
        }
    }
}