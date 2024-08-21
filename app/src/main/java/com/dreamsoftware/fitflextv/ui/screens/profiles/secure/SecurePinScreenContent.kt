package com.dreamsoftware.fitflextv.ui.screens.profiles.secure

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Security
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fudge.component.FudgeTvFocusRequester
import com.dreamsoftware.fudge.component.FudgeTvText
import com.dreamsoftware.fudge.component.FudgeTvTextField
import com.dreamsoftware.fudge.component.FudgeTvTextFieldTypeEnum
import com.dreamsoftware.fudge.component.FudgeTvTextTypeEnum
import com.dreamsoftware.fudge.component.profiles.FudgeTvProfileScreenContent

@Composable
internal fun SecurePinScreenContent(
    uiState: SecurePinUiState,
    actionListener: SecurePinScreenActionListener
) {
    with(uiState) {
        FudgeTvProfileScreenContent(
            isLoading = isLoading,
            error = errorMessage,
            mainLogoRes = R.drawable.main_logo,
            mainLogoInverseRes = R.drawable.main_logo_inverse,
            loadingTitleRes = R.string.generic_progress_dialog_title,
            loadingDescriptionRes = R.string.generic_progress_dialog_description,
            mainTitleRes = R.string.secure_pin_main_title,
            secondaryTitleRes = R.string.secure_pin_main_description,
            primaryOptionTextRes = R.string.secure_pin_form_accept_button_text,
            secondaryOptionTextRes = R.string.secure_pin_form_cancel_button_text,
            onPrimaryOptionPressed = actionListener::onVerifyPressed,
            onSecondaryOptionPressed = actionListener::onCancelPressed,
            onErrorAccepted = actionListener::onErrorMessageCleared
        ) {
            FudgeTvFocusRequester { focusRequester ->
                FudgeTvTextField(
                    modifier = Modifier.focusRequester(focusRequester),
                    icon = Icons.Filled.Security,
                    value = unlockPin,
                    type = FudgeTvTextFieldTypeEnum.NUMBER_SECRET,
                    imeAction = ImeAction.Done,
                    onImeActionCompleted = {
                        actionListener.onVerifyPressed()
                    },
                    labelRes = R.string.secure_pin_form_label_text,
                    onValueChange = actionListener::onUnlockPinChanged
                )
            }
            profileLocked?.let {
                FudgeTvText(
                    type = FudgeTvTextTypeEnum.BODY_MEDIUM,
                    titleText = stringResource(id = R.string.secure_pin_info_profile_locked, it.alias),
                    textBold = true
                )
            }
        }
    }
}