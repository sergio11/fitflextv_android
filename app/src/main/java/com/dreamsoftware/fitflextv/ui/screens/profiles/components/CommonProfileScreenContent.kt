package com.dreamsoftware.fitflextv.ui.screens.profiles.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fudge.component.FudgeTvButton
import com.dreamsoftware.fudge.component.FudgeTvButtonStyleTypeEnum
import com.dreamsoftware.fudge.component.FudgeTvImageRes
import com.dreamsoftware.fudge.component.FudgeTvLoadingDialog
import com.dreamsoftware.fudge.component.FudgeTvScreenContent
import com.dreamsoftware.fudge.component.FudgeTvText
import com.dreamsoftware.fudge.component.FudgeTvTextTypeEnum

@Composable
fun CommonProfileScreenContent(
    isLoading: Boolean? = null,
    error: String?,
    onErrorAccepted: () -> Unit,
    @StringRes mainTitleRes: Int? = null,
    mainTitleText: String? = null,
    @StringRes secondaryTitleRes: Int? = null,
    secondaryTitleText: String? = null,
    @StringRes primaryOptionTextRes: Int,
    @StringRes secondaryOptionTextRes: Int? = null,
    @StringRes tertiaryOptionTextRes: Int? = null,
    onPrimaryOptionPressed: () -> Unit = {},
    onSecondaryOptionPressed: () -> Unit = {},
    onTertiaryOptionPressed: () -> Unit = {},
    content: @Composable (mainActionFocusRequester: FocusRequester) -> Unit
) {
    val mainActionFocusRequester = remember { FocusRequester() }
    FudgeTvScreenContent(
        error = error,
        onErrorAccepted = onErrorAccepted
    ) {
        CommonProfileGradientBox {
            isLoading?.let {
                FudgeTvLoadingDialog(
                    isShowingDialog = it,
                    mainLogoRes = R.drawable.main_logo,
                    titleRes = R.string.generic_progress_dialog_title,
                    descriptionRes = R.string.generic_progress_dialog_description
                )
            }
            FudgeTvImageRes(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(20.dp)
                    .height(90.dp),
                imageRes = R.drawable.main_logo_inverse
            )
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxHeight(0.95f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CommonProfileHeader(
                    mainTitleRes = mainTitleRes,
                    mainTitleText = mainTitleText,
                    secondaryTitleRes = secondaryTitleRes,
                    secondaryTitleText = secondaryTitleText
                )
                CommonProfileContent(
                    content = { content(mainActionFocusRequester) }
                )
                CommonProfileActions(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(0.3f),
                    focusRequester = mainActionFocusRequester,
                    primaryOptionTextRes = primaryOptionTextRes,
                    secondaryOptionTextRes = secondaryOptionTextRes,
                    tertiaryOptionTextRes = tertiaryOptionTextRes,
                    onPrimaryOptionPressed = onPrimaryOptionPressed,
                    onSecondaryOptionPressed = onSecondaryOptionPressed,
                    onTertiaryOptionPressed = onTertiaryOptionPressed
                )
            }
        }
    }
}

@Composable
private fun ColumnScope.CommonProfileHeader(
    @StringRes mainTitleRes: Int? = null,
    mainTitleText: String? = null,
    @StringRes secondaryTitleRes: Int? = null,
    secondaryTitleText: String? = null,
) {
    Spacer(modifier = Modifier.weight(0.2f))
    FudgeTvText(
        titleRes = mainTitleRes,
        titleText = mainTitleText,
        type = FudgeTvTextTypeEnum.TITLE_LARGE
    )
    Spacer(modifier = Modifier.height(10.dp))
    FudgeTvText(
        titleRes = secondaryTitleRes,
        titleText = secondaryTitleText,
        type = FudgeTvTextTypeEnum.TITLE_MEDIUM,
        textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.weight(0.1f))
}

@Composable
private fun ColumnScope.CommonProfileContent(
    content: @Composable () -> Unit
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .weight(0.9f),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally) {
        content()
    }
}

@Composable
private fun CommonProfileActions(
    modifier: Modifier = Modifier,
    @StringRes primaryOptionTextRes: Int,
    @StringRes secondaryOptionTextRes: Int? = null,
    @StringRes tertiaryOptionTextRes: Int? = null,
    onPrimaryOptionPressed: () -> Unit = {},
    onSecondaryOptionPressed: () -> Unit = {},
    onTertiaryOptionPressed: () -> Unit = {},
    focusRequester: FocusRequester
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            FudgeTvButton(
                modifier = Modifier.focusRequester(focusRequester),
                textRes = primaryOptionTextRes,
                onClick = onPrimaryOptionPressed,
            )
            secondaryOptionTextRes?.let {
                Spacer(modifier = Modifier.width(30.dp))
                FudgeTvButton(
                    textRes = it,
                    onClick = onSecondaryOptionPressed,
                    style = FudgeTvButtonStyleTypeEnum.INVERSE
                )
            }
        }
        tertiaryOptionTextRes?.let {
            FudgeTvButton(
                modifier = Modifier.width(250.dp),
                textRes = it,
                enableBorder = false,
                onClick = onTertiaryOptionPressed,
                style = FudgeTvButtonStyleTypeEnum.TRANSPARENT
            )
        }
    }
}