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
import androidx.compose.ui.unit.dp
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.ui.core.components.CommonButton
import com.dreamsoftware.fitflextv.ui.core.components.CommonButtonStyleTypeEnum
import com.dreamsoftware.fitflextv.ui.core.components.CommonImageRes
import com.dreamsoftware.fitflextv.ui.core.components.CommonScreenContent
import com.dreamsoftware.fitflextv.ui.core.components.CommonText
import com.dreamsoftware.fitflextv.ui.core.components.CommonTextTypeEnum
import com.dreamsoftware.fitflextv.ui.core.components.LoadingDialog

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
    CommonScreenContent(
        error = error,
        onErrorAccepted = onErrorAccepted
    ) {
        CommonProfileGradientBox {
            isLoading?.let {
                LoadingDialog(
                    isShowingDialog = it,
                    titleRes = R.string.generic_progress_dialog_title,
                    descriptionRes = R.string.generic_progress_dialog_description
                )
            }
            CommonImageRes(
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
    CommonText(
        titleRes = mainTitleRes,
        titleText = mainTitleText,
        type = CommonTextTypeEnum.TITLE_LARGE
    )
    Spacer(modifier = Modifier.height(10.dp))
    CommonText(
        titleRes = secondaryTitleRes,
        titleText = secondaryTitleText,
        type = CommonTextTypeEnum.TITLE_MEDIUM
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
            CommonButton(
                modifier = Modifier.focusRequester(focusRequester),
                textRes = primaryOptionTextRes,
                onClick = onPrimaryOptionPressed,
            )
            secondaryOptionTextRes?.let {
                Spacer(modifier = Modifier.width(30.dp))
                CommonButton(
                    textRes = it,
                    onClick = onSecondaryOptionPressed,
                    style = CommonButtonStyleTypeEnum.INVERSE
                )
            }
        }
        tertiaryOptionTextRes?.let {
            CommonButton(
                textRes = it,
                enableBorder = false,
                onClick = onTertiaryOptionPressed,
                style = CommonButtonStyleTypeEnum.TRANSPARENT
            )
        }
    }
}