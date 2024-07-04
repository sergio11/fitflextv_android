package com.dreamsoftware.fitflextv.ui.screens.onboarding

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.ui.core.components.CommonButton
import com.dreamsoftware.fitflextv.ui.core.components.CommonButtonStyleTypeEnum
import com.dreamsoftware.fitflextv.ui.core.components.CommonButtonTypeEnum
import com.dreamsoftware.fitflextv.ui.core.components.CommonFocusRequester
import com.dreamsoftware.fitflextv.ui.core.components.CommonText
import com.dreamsoftware.fitflextv.ui.core.components.CommonTextTypeEnum
import com.dreamsoftware.fitflextv.ui.core.components.CommonVideoBackground
import com.dreamsoftware.fitflextv.ui.core.components.ExitAppDialog

@Composable
fun OnboardingScreenContent(
    modifier: Modifier = Modifier,
    onGoToSignIn: () -> Unit,
    onGoToSignUp: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        OnboardingVideoBackground()
        ConfirmExitAppDialog()
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.9f)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f)
            ) {
                val commonModifier = Modifier
                    .fillMaxSize()
                    .weight(0.8f)
                OnBoardingLogo(modifier = commonModifier)
                OnboardingContentInfo(modifier = commonModifier)
            }
            OnBoardingActions(
                modifier = Modifier
                    .fillMaxWidth(),
                onGoToSignIn = onGoToSignIn,
                onGoToSignUp = onGoToSignUp
            )
        }
    }
}

@Composable
private fun ConfirmExitAppDialog() {
    var confirmExitApp by remember { mutableStateOf(false) }
    BackHandler { confirmExitApp = true }
    ExitAppDialog(
        isVisible = confirmExitApp,
        onDismissPressed = {
            confirmExitApp = false
        },
        onExitPressed = {
            confirmExitApp = false
        }
    )
}

@Composable
private fun OnBoardingLogo(modifier: Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.main_logo_inverse),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .height(210.dp)
                .align(Alignment.TopCenter)
                .padding(top = 60.dp)
        )
    }
}

@Composable
private fun OnboardingContentInfo(modifier: Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CommonText(
                titleRes = R.string.onboarding_main_title_text,
                type = CommonTextTypeEnum.HEADLINE_LARGE,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            CommonText(
                titleRes = R.string.onboarding_secondary_title_text,
                type = CommonTextTypeEnum.BODY_LARGE,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(50.dp))
            CommonText(
                titleRes = R.string.onboarding_additional_info_text,
                type = CommonTextTypeEnum.HEADLINE_SMALL,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun OnboardingVideoBackground() {
    CommonVideoBackground(videoResourceId = R.raw.onboarding_video)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.7f))
    )
}

@Composable
private fun OnBoardingActions(
    modifier: Modifier,
    onGoToSignIn: () -> Unit,
    onGoToSignUp: () -> Unit
) {
    CommonFocusRequester { requester ->
        Row (
            modifier = modifier,
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                CommonText(
                    titleRes = R.string.developer_credits_text,
                    type = CommonTextTypeEnum.LABEL_MEDIUM,
                    textAlign = TextAlign.Center
                )
            }
            CommonButton(
                modifier = Modifier.focusRequester(requester),
                type = CommonButtonTypeEnum.LARGE,
                textRes = R.string.onboarding_sign_in_button_text,
                onClick = onGoToSignIn,
            )
            Spacer(modifier = Modifier.width(30.dp))
            CommonButton(
                type = CommonButtonTypeEnum.LARGE,
                onClick = onGoToSignUp,
                textRes = R.string.onboarding_sign_up_button_text,
                style = CommonButtonStyleTypeEnum.INVERSE
            )
        }
    }
}