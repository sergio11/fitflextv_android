package com.dreamsoftware.fitflextv.ui.screens.profiles.management

import androidx.compose.runtime.Composable
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.domain.model.ProfileBO
import com.dreamsoftware.fitflextv.ui.screens.profiles.components.CommonProfileScreenContent
import com.dreamsoftware.fitflextv.ui.screens.profiles.components.CommonProfileSelector

@Composable
fun ProfilesManagementScreenContent(
    uiState: ProfilesManagementUiState,
    onCompletePressed: () -> Unit,
    onProfileSelected: (ProfileBO) -> Unit,
    onErrorAccepted: () -> Unit
) {
    with(uiState) {
        CommonProfileScreenContent(
            isLoading = isLoading,
            error = errorMessage,
            mainTitleRes = R.string.profiles_management_main_title,
            secondaryTitleRes = R.string.profiles_management_main_description,
            primaryOptionTextRes = R.string.profiles_management_form_confirm_button_text,
            onPrimaryOptionPressed = onCompletePressed,
            onErrorAccepted = onErrorAccepted
        ) {
            CommonProfileSelector(
                profiles = uiState.profiles,
                editMode = true,
                onProfileSelected = onProfileSelected
            )
        }
    }
}