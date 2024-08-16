package com.dreamsoftware.fitflextv.ui.screens.profiles.changesecurepin

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.runtime.Composable
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.ui.core.components.CommonTextField
import com.dreamsoftware.fitflextv.ui.core.components.CommonTextFieldTypeEnum
import com.dreamsoftware.fitflextv.ui.screens.profiles.components.CommonProfileScreenContent

@Composable
fun ChangeSecurePinScreenContent(
    uiState: ChangeSecurePinUiState,
    actionListener: ChangeSecurePinActionListener
) {
    with(uiState) {
        CommonProfileScreenContent(
            isLoading = isLoading,
            error = errorMessage,
            onErrorAccepted = actionListener::onErrorAccepted,
            mainTitleRes = R.string.profiles_change_secure_pin_main_title,
            secondaryTitleRes = R.string.profiles_change_secure_pin_main_description,
            primaryOptionTextRes = R.string.profiles_change_secure_pin_form_confirm_button_text,
            secondaryOptionTextRes = R.string.profiles_change_secure_pin_delete_button_text,
            onPrimaryOptionPressed = actionListener::onConfirmPressed,
            onSecondaryOptionPressed = actionListener::onDeleteProfilePressed
        ) {
            CommonTextField(
                icon = Icons.Filled.Key,
                value = currentSecurePin,
                errorMessage = currentSecurePinError,
                type = CommonTextFieldTypeEnum.NUMBER_SECRET,
                labelRes = R.string.profiles_change_secure_pin_form_current_pin_label_text,
                onValueChange = actionListener::onCurrentSecurePinChanged
            )
            CommonTextField(
                icon = Icons.Filled.Key,
                value = newSecurePin,
                errorMessage = newSecurePinError,
                type = CommonTextFieldTypeEnum.NUMBER_SECRET,
                labelRes = R.string.profiles_change_secure_pin_form_new_pin_label_text,
                onValueChange = actionListener::onNewSecurePinChanged
            )
        }
    }
}