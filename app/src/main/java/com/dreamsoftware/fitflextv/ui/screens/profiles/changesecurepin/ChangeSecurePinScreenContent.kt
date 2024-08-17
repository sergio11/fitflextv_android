package com.dreamsoftware.fitflextv.ui.screens.profiles.changesecurepin

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.runtime.Composable
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fudge.component.FudgeTvDialog
import com.dreamsoftware.fudge.component.FudgeTvTextField
import com.dreamsoftware.fudge.component.FudgeTvTextFieldTypeEnum
import com.dreamsoftware.fudge.component.profiles.FudgeTvProfileScreenContent

@Composable
fun ChangeSecurePinScreenContent(
    uiState: ChangeSecurePinUiState,
    actionListener: ChangeSecurePinActionListener
) {
    with(uiState) {
        FudgeTvProfileScreenContent(
            isLoading = isLoading,
            error = errorMessage,
            mainLogoRes = R.drawable.main_logo,
            mainLogoInverseRes = R.drawable.main_logo_inverse,
            loadingTitleRes = R.string.generic_progress_dialog_title,
            loadingDescriptionRes = R.string.generic_progress_dialog_description,
            onErrorAccepted = actionListener::onErrorMessageCleared,
            mainTitleRes = R.string.profiles_change_secure_pin_main_title,
            secondaryTitleRes = R.string.profiles_change_secure_pin_main_description,
            primaryOptionTextRes = R.string.profiles_change_secure_pin_form_confirm_button_text,
            secondaryOptionTextRes = R.string.profiles_change_secure_pin_delete_button_text,
            onPrimaryOptionPressed = actionListener::onConfirmPressed,
            onSecondaryOptionPressed = actionListener::onDeleteProfilePressed
        ) {
            FudgeTvDialog(
                isVisible = showSecurePinUpdatedDialog,
                mainLogoRes = R.drawable.main_logo,
                titleRes = R.string.profiles_change_secure_updated_dialog_title,
                descriptionRes = R.string.profiles_change_secure_updated_dialog_description,
                onAcceptClicked = actionListener::onCloseSecurePinUpdatedDialog,
            )
            FudgeTvTextField(
                icon = Icons.Filled.Key,
                value = currentSecurePin,
                errorMessage = currentSecurePinError,
                type = FudgeTvTextFieldTypeEnum.NUMBER_SECRET,
                labelRes = R.string.profiles_change_secure_pin_form_current_pin_label_text,
                onValueChange = actionListener::onCurrentSecurePinChanged
            )
            FudgeTvTextField(
                icon = Icons.Filled.Key,
                value = newSecurePin,
                errorMessage = newSecurePinError,
                type = FudgeTvTextFieldTypeEnum.NUMBER_SECRET,
                labelRes = R.string.profiles_change_secure_pin_form_new_pin_label_text,
                onValueChange = actionListener::onNewSecurePinChanged
            )
        }
    }
}