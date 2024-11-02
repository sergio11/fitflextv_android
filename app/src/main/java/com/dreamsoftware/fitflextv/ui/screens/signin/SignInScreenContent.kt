package com.dreamsoftware.fitflextv.ui.screens.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
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
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
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
internal fun SignInScreenContent(
    uiState: SignInUiState,
    actionListener: SignInScreenActionListener
) {
    FudgeTvScreenContent(
        error = uiState.errorMessage,
        onErrorAccepted = actionListener::onErrorMessageCleared
    ) {
        SignInDialog(uiState = uiState)
        SignInVideoBackground()
        SignInLogo()
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.9f),
            verticalArrangement = Arrangement.Center,
        ) {
            SignInMainContent(
                uiState = uiState,
                onEmailChanged = actionListener::onEmailChanged,
                onPasswordChanged = actionListener::onPasswordChanged,
                onSigInPressed = actionListener::onSigInPressed
            )
            SignInSecondaryContent(onGoToSignUp = actionListener::onGoToSignUp)
        }
    }
}

@Composable
private fun SignInDialog(
    uiState: SignInUiState
) {
    with(uiState) {
        FudgeTvLoadingDialog(
            isShowingDialog = isLoading,
            mainLogoRes = R.drawable.main_logo,
            titleRes = R.string.sign_in_progress_dialog_title,
            descriptionRes = R.string.sign_in_progress_dialog_description
        )
    }
}

@Composable
private fun BoxScope.SignInLogo() {
    Image(
        painter = painterResource(id = R.drawable.main_logo_inverse),
        contentDescription = null,
        modifier = Modifier
            .padding(25.dp)
            .height(120.dp)
            .align(Alignment.TopStart)
    )
}

@Composable
private fun SignInMainContent(
    uiState: SignInUiState,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onSigInPressed: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.7f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        val commonModifier = Modifier
            .fillMaxHeight()
            .weight(1f)
        SignInFormInfo(modifier = commonModifier)
        SignInFormContent(
            modifier = commonModifier,
            uiState = uiState,
            onEmailChanged = onEmailChanged,
            onPasswordChanged = onPasswordChanged,
            onSigInPressed = onSigInPressed
        )
    }
}

@Composable
private fun ColumnScope.SignInSecondaryContent(onGoToSignUp: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .fillMaxHeight(0.3f)
            .align(Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            FudgeTvText(
                titleRes = R.string.developer_credits_text,
                type = FudgeTvTextTypeEnum.LABEL_MEDIUM,
                textAlign = TextAlign.Center
            )
        }
        FudgeTvText(
            titleRes = R.string.sign_in_do_not_have_account_yet_text,
            type = FudgeTvTextTypeEnum.LABEL_LARGE
        )
        Spacer(modifier = Modifier.width(20.dp))
        FudgeTvButton(
            textRes = R.string.sign_in_go_sign_up_button_text,
            type = FudgeTvButtonTypeEnum.SMALL,
            style = FudgeTvButtonStyleTypeEnum.TRANSPARENT,
            onClick = onGoToSignUp
        )
    }
}

@Composable
private fun SignInVideoBackground() {
    FudgeTvFullScreenImage(resourceId = R.drawable.login_background)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background.copy(alpha = 0.8f))
    )
}


@Composable
private fun SignInFormInfo(modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        FudgeTvText(
            titleRes = R.string.sign_in_main_title_text,
            type = FudgeTvTextTypeEnum.HEADLINE_MEDIUM,
            textAlign = TextAlign.Center,
            textBold = true
        )
        Spacer(modifier = Modifier.height(20.dp))
        FudgeTvText(
            titleRes = R.string.sign_in_secondary_title_text,
            type = FudgeTvTextTypeEnum.BODY_LARGE,
            textAlign = TextAlign.Center,
            textBold = true
        )
    }
}


@Composable
private fun SignInFormContent(
    modifier: Modifier,
    uiState: SignInUiState,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onSigInPressed: () -> Unit
) {
    with(uiState) {
        FudgeTvFocusRequester { requester ->
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                FudgeTvText(
                    titleRes = R.string.sign_in_form_heading_text,
                    type = FudgeTvTextTypeEnum.HEADLINE_MEDIUM,
                )
                Spacer(modifier = Modifier.height(20.dp))
                FudgeTvTextField(
                    icon = Icons.Filled.Person,
                    value = email,
                    type = FudgeTvTextFieldTypeEnum.EMAIL,
                    labelRes = R.string.sign_in_form_email_label_text,
                    errorMessage = emailError,
                    onValueChange = onEmailChanged
                )
                Spacer(modifier = Modifier.height(20.dp))
                FudgeTvTextField(
                    icon = Icons.Filled.Password,
                    value = password,
                    type = FudgeTvTextFieldTypeEnum.PASSWORD,
                    labelRes = R.string.sign_in_form_password_label_text,
                    errorMessage = passwordError,
                    onValueChange = onPasswordChanged
                )
                Spacer(modifier = Modifier.height(40.dp))
                FudgeTvButton(
                    modifier = Modifier.focusRequester(requester),
                    onClick = onSigInPressed,
                    textRes = R.string.sign_in_main_button_text
                )
            }
        }
    }
}

@Preview(device = Devices.TV_1080p)
@Composable
fun SignInScreenContentPrev() {
    FitFlexTVTheme {
        SignInScreenContent(
            uiState = SignInUiState(),
            actionListener = object : SignInScreenActionListener {
                override fun onEmailChanged(newEmail: String) {}
                override fun onPasswordChanged(newPassword: String) {}
                override fun onSigInPressed() {}
                override fun onGoToSignUp() {}
            }
        )
    }
}