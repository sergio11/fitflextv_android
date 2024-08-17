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
import com.dreamsoftware.fitflextv.ui.core.components.ExitAppDialog
import com.dreamsoftware.fudge.component.FudgeTvButton
import com.dreamsoftware.fudge.component.FudgeTvButtonStyleTypeEnum
import com.dreamsoftware.fudge.component.FudgeTvButtonTypeEnum
import com.dreamsoftware.fudge.component.FudgeTvFocusRequester
import com.dreamsoftware.fudge.component.FudgeTvText
import com.dreamsoftware.fudge.component.FudgeTvTextTypeEnum
import com.dreamsoftware.fudge.component.FudgeTvVideoBackground

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
            FudgeTvText(
                titleRes = R.string.onboarding_main_title_text,
                type = FudgeTvTextTypeEnum.HEADLINE_LARGE,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 8.dp),
                textBold = true
            )
            Spacer(modifier = Modifier.height(20.dp))
            FudgeTvText(
                titleRes = R.string.onboarding_secondary_title_text,
                type = FudgeTvTextTypeEnum.BODY_LARGE,
                textAlign = TextAlign.Center,
                textBold = true
            )
            Spacer(modifier = Modifier.height(50.dp))
            FudgeTvText(
                titleRes = R.string.onboarding_additional_info_text,
                type = FudgeTvTextTypeEnum.HEADLINE_SMALL,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun OnboardingVideoBackground() {
    FudgeTvVideoBackground(videoResourceId = R.raw.onboarding_video)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background.copy(alpha = 0.8f))
    )
}

@Composable
private fun OnBoardingActions(
    modifier: Modifier,
    onGoToSignIn: () -> Unit,
    onGoToSignUp: () -> Unit
) {
    FudgeTvFocusRequester { requester ->
        Row (
            modifier = modifier,
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ){
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
            FudgeTvButton(
                modifier = Modifier.focusRequester(requester),
                type = FudgeTvButtonTypeEnum.LARGE,
                textRes = R.string.onboarding_sign_in_button_text,
                onClick = onGoToSignIn,
            )
            Spacer(modifier = Modifier.width(30.dp))
            FudgeTvButton(
                type = FudgeTvButtonTypeEnum.LARGE,
                onClick = onGoToSignUp,
                textRes = R.string.onboarding_sign_up_button_text,
                style = FudgeTvButtonStyleTypeEnum.INVERSE
            )
        }
    }
}