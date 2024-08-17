package com.dreamsoftware.fitflextv.ui.screens.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.ui.theme.FitFlexTVTheme
import com.dreamsoftware.fudge.component.FudgeTvButton
import com.dreamsoftware.fudge.component.FudgeTvButtonStyleTypeEnum
import com.dreamsoftware.fudge.component.FudgeTvButtonTypeEnum
import com.dreamsoftware.fudge.component.FudgeTvFocusRequester
import com.dreamsoftware.fudge.component.FudgeTvFullScreenImage
import com.dreamsoftware.fudge.component.FudgeTvLoadingDialog
import com.dreamsoftware.fudge.component.FudgeTvScreenContent
import com.dreamsoftware.fudge.component.FudgeTvText
import com.dreamsoftware.fudge.component.FudgeTvTextField
import com.dreamsoftware.fudge.component.FudgeTvTextFieldTypeEnum
import com.dreamsoftware.fudge.component.FudgeTvTextTypeEnum

@Composable
fun SignUpScreenContent(
    uiState: SignUpUiState,
    onFirstNameChanged: (String) -> Unit,
    onLastNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onUsernameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onRepeatPasswordChanged: (String) -> Unit,
    onSigUpPressed: () -> Unit,
    onCancelPressed: () -> Unit,
    onErrorAccepted: () -> Unit
) {
    with(uiState) {
        FudgeTvScreenContent(
            error = errorMessage,
            onErrorAccepted = onErrorAccepted
        ) {
            SignUpDialog(uiState = uiState)
            SignUpBackground()
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.8f),
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {
                SignUpFormContent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.8f),
                    uiState = uiState,
                    onFirstNameChanged = onFirstNameChanged,
                    onLastNameChanged = onLastNameChanged,
                    onEmailChanged = onEmailChanged,
                    onUsernameChanged = onUsernameChanged,
                    onPasswordChanged = onPasswordChanged,
                    onRepeatPasswordChanged = onRepeatPasswordChanged,
                    onCancelPressed = onCancelPressed,
                    onSigUpPressed = onSigUpPressed
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.2f),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FudgeTvText(
                        titleRes = R.string.developer_credits_text_single_line,
                        type = FudgeTvTextTypeEnum.LABEL_SMALL,
                        textAlign = TextAlign.Start
                    )
                }
            }
        }
    }
}

@Composable
private fun SignUpDialog(
    uiState: SignUpUiState
) {
    with(uiState) {
        FudgeTvLoadingDialog(
            isShowingDialog = isLoading,
            mainLogoRes = R.drawable.main_logo,
            titleRes = R.string.sign_up_progress_dialog_title,
            descriptionRes = R.string.sign_up_progress_dialog_description
        )
    }
}

@Composable
private fun SignUpBackground() {
    FudgeTvFullScreenImage(resourceId = R.drawable.signup_background)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background.copy(alpha = 0.8f))
    )
}

@Composable
private fun SignUpFormContent(
    modifier: Modifier,
    uiState: SignUpUiState,
    onFirstNameChanged: (String) -> Unit,
    onLastNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onUsernameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onRepeatPasswordChanged: (String) -> Unit,
    onSigUpPressed: () -> Unit,
    onCancelPressed: () -> Unit
) {
    Column(
        modifier = modifier.padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            FudgeTvButton(
                modifier = Modifier.padding(end = 20.dp),
                type = FudgeTvButtonTypeEnum.SMALL,
                onClick = onCancelPressed,
                style = FudgeTvButtonStyleTypeEnum.TRANSPARENT,
                text = "Cancel"
            )
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FudgeTvText(
                    titleRes = R.string.sign_up_main_title_text,
                    type = FudgeTvTextTypeEnum.TITLE_LARGE,
                )
                Spacer(modifier = Modifier.height(10.dp))
                FudgeTvText(
                    titleRes = R.string.sign_up_secondary_title_text,
                    type = FudgeTvTextTypeEnum.TITLE_SMALL,
                    textAlign = TextAlign.Center
                )
            }
            Image(
                painter = painterResource(id = R.drawable.main_logo_inverse),
                contentDescription = null,
                modifier = Modifier
                    .height(80.dp)
                    .padding(start = 20.dp),
            )
        }
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            UserInfoFormColumn(
                modifier = Modifier.fillMaxHeight(),
                uiState = uiState,
                onFirstNameChanged = onFirstNameChanged,
                onLastNameChanged = onLastNameChanged,
                onEmailChanged = onEmailChanged
            )
            Spacer(modifier = Modifier.width(40.dp))
            UserCredentialsInfoFormColumn(
                modifier = Modifier.fillMaxHeight(),
                uiState = uiState,
                onUsernameChanged = onUsernameChanged,
                onPasswordChanged = onPasswordChanged,
                onRepeatPasswordChanged = onRepeatPasswordChanged
            )
        }
        FudgeTvButton(
            onClick = onSigUpPressed,
            textRes = R.string.sign_up_main_button
        )
    }
}

@Composable
private fun UserInfoFormColumn(
    modifier: Modifier,
    uiState: SignUpUiState,
    onFirstNameChanged: (String) -> Unit,
    onLastNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit
) {
    with(uiState) {
        FudgeTvFocusRequester { focusRequester ->
            FormColumn(modifier = modifier) {
                FudgeTvTextField(
                    modifier = Modifier.focusRequester(focusRequester),
                    icon = Icons.Filled.Person,
                    value = firstName,
                    labelRes = R.string.sign_up_form_first_name_label_text,
                    errorMessage = firstNameError,
                    onValueChange = onFirstNameChanged
                )
                FudgeTvTextField(
                    icon = Icons.Filled.PersonOutline,
                    value = lastName,
                    labelRes = R.string.sign_up_form_surname_label_text,
                    errorMessage = lastNameError,
                    onValueChange = onLastNameChanged
                )
                FudgeTvTextField(
                    icon = Icons.Filled.Email,
                    value = email,
                    type = FudgeTvTextFieldTypeEnum.EMAIL,
                    labelRes = R.string.sign_up_form_email_label_text,
                    errorMessage = emailError,
                    onValueChange = onEmailChanged
                )
            }
        }
    }
}

@Composable
private fun UserCredentialsInfoFormColumn(
    modifier: Modifier,
    uiState: SignUpUiState,
    onUsernameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onRepeatPasswordChanged: (String) -> Unit
) {
    with(uiState) {
        FormColumn(modifier = modifier) {
            FudgeTvTextField(
                icon = Icons.Filled.Person,
                value = username,
                labelRes = R.string.sign_up_form_username_label_text,
                errorMessage = usernameError,
                onValueChange = onUsernameChanged
            )
            FudgeTvTextField(
                icon = Icons.Filled.Password,
                value = password,
                type = FudgeTvTextFieldTypeEnum.PASSWORD,
                labelRes = R.string.sign_up_form_password_label_text,
                errorMessage = passwordError,
                onValueChange = onPasswordChanged
            )
            FudgeTvTextField(
                icon = Icons.Filled.Password,
                value = repeatPassword,
                type = FudgeTvTextFieldTypeEnum.PASSWORD,
                labelRes = R.string.sign_up_form_repeat_password_label_text,
                errorMessage = repeatPasswordError,
                onValueChange = onRepeatPasswordChanged
            )
        }
    }
}

@Composable
private fun FormColumn(
    modifier: Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = content
    )
}

@Preview(device = Devices.TV_1080p)
@Composable
fun SignUpPrev() {
    FitFlexTVTheme {
        SignUpScreenContent(
            uiState = SignUpUiState(),
            onFirstNameChanged = {},
            onLastNameChanged = {},
            onEmailChanged = {},
            onUsernameChanged = {},
            onPasswordChanged = {},
            onRepeatPasswordChanged = {},
            onSigUpPressed = {},
            onCancelPressed = {},
            onErrorAccepted = {}
        )
    }
}