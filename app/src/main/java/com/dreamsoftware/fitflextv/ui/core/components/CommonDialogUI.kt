package com.dreamsoftware.fitflextv.ui.core.components

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.CardShape
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.fitflextv.R

@Composable
fun CommonDialog(
    isVisible: Boolean,
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int? = null,
    @StringRes descriptionRes: Int? = null,
    title: String? = null,
    description: String? = null,
    @StringRes cancelRes: Int = R.string.dialog_generic_cancel_button,
    @StringRes successRes: Int = R.string.dialog_generic_accept_button,
    onCancelClicked: (() -> Unit)? = null,
    onAcceptClicked: (() -> Unit)? = null,
    isAcceptEnabled: Boolean = true,
    customContent: @Composable ColumnScope.() -> Unit = {}
) {
    if(isVisible) {
        Dialog(
            onDismissRequest = onCancelClicked ?: {}
        ) {
            CommonDialogUI(
                modifier = modifier,
                title = titleRes?.let { stringResource(id = it) } ?: title,
                description = descriptionRes?.let { stringResource(id = it) } ?: description,
                cancelRes = cancelRes,
                successRes = successRes,
                onCancelClicked = onCancelClicked,
                onAcceptClicked = onAcceptClicked,
                isAcceptEnabled = isAcceptEnabled,
                customContent = customContent
            )
        }
    }
}

@Composable
private fun CommonDialogUI(
    modifier: Modifier = Modifier,
    title: String? = null,
    description: String? = null,
    @StringRes cancelRes: Int,
    @StringRes successRes: Int,
    onCancelClicked: (() -> Unit)? = null,
    onAcceptClicked: (() -> Unit)? = null,
    isAcceptEnabled: Boolean = true,
    customContent: @Composable ColumnScope.() -> Unit = {}
) {
    with(MaterialTheme.colorScheme) {
        Card(
            onClick = { },
            modifier = modifier
                .background(Color.Transparent, RoundedCornerShape(16.dp))
                .padding(bottom = 8.dp),
        ) {
            Column(
                modifier = modifier.background(inverseSurface),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = painterResource(id = R.drawable.main_logo),
                    contentDescription = null, // decorative
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .size(150.dp)
                )
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    CommonText(
                        type = CommonTextTypeEnum.TITLE_LARGE,
                        titleText = title,
                        modifier = Modifier
                            .fillMaxWidth(),
                        maxLines = 2,
                        textColor = inverseOnSurface,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    CommonText(
                        modifier = Modifier
                            .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                            .fillMaxWidth(),
                        type = CommonTextTypeEnum.BODY_MEDIUM,
                        titleText = description,
                        textColor = inverseOnSurface,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                customContent()
                Spacer(modifier = Modifier.height(30.dp))
                //.......................................................................
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .background(background.copy(0.2F)),
                    horizontalArrangement = Arrangement.Center
                ) {
                    onCancelClicked?.let {
                        CommonButton(
                            type = CommonButtonTypeEnum.MEDIUM,
                            onClick = it,
                            textRes = cancelRes,
                            style = CommonButtonStyleTypeEnum.INVERSE
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    onAcceptClicked?.let {
                        CommonButton(
                            type = CommonButtonTypeEnum.MEDIUM,
                            onClick = it,
                            enabled = isAcceptEnabled,
                            textRes = successRes
                        )
                    }
                }
            }
        }
    }
}