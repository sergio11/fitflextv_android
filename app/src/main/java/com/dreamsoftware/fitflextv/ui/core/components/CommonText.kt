package com.dreamsoftware.fitflextv.ui.core.components

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.dreamsoftware.fitflextv.R

private const val DEFAULT_MAX_LINES = Int.MAX_VALUE

enum class CommonTextTypeEnum {
    TITLE_LARGE,
    TITLE_MEDIUM,
    TITLE_SMALL,
    LABEL_LARGE,
    LABEL_MEDIUM,
    LABEL_SMALL,
    BODY_SMALL,
    BODY_MEDIUM,
    BODY_LARGE,
    HEADLINE_SMALL,
    HEADLINE_MEDIUM,
    HEADLINE_LARGE
}

@Composable
fun CommonText(
    modifier: Modifier = Modifier,
    type: CommonTextTypeEnum,
    @StringRes titleRes: Int? = null,
    titleText: String? = null,
    singleLine: Boolean = false,
    textBold: Boolean = false,
    maxLines: Int = DEFAULT_MAX_LINES,
    textColor: Color? = null,
    textAlign: TextAlign? = null
) {
    with(MaterialTheme.colorScheme) {
        CommonTextComponent(
            modifier = modifier,
            singleLine = singleLine,
            text = titleRes?.let {
                stringResource(id = it)
            } ?: titleText ?: stringResource(id = R.string.no_text_value),
            maxLines = maxLines,
            textColor = textColor ?: onPrimary,
            textAlign = textAlign,
            textBold = textBold,
            textStyle = with(MaterialTheme.typography) {
                when (type) {
                    CommonTextTypeEnum.TITLE_LARGE -> titleLarge
                    CommonTextTypeEnum.TITLE_MEDIUM -> titleMedium
                    CommonTextTypeEnum.TITLE_SMALL -> titleSmall
                    CommonTextTypeEnum.LABEL_LARGE -> labelLarge
                    CommonTextTypeEnum.LABEL_MEDIUM -> labelMedium
                    CommonTextTypeEnum.LABEL_SMALL -> labelSmall
                    CommonTextTypeEnum.BODY_SMALL -> bodySmall
                    CommonTextTypeEnum.BODY_MEDIUM -> bodyMedium
                    CommonTextTypeEnum.BODY_LARGE -> bodyLarge
                    CommonTextTypeEnum.HEADLINE_SMALL -> headlineSmall
                    CommonTextTypeEnum.HEADLINE_MEDIUM -> headlineMedium
                    CommonTextTypeEnum.HEADLINE_LARGE -> headlineLarge
                }
            }
        )
    }
}

@Composable
private fun CommonTextComponent(
    modifier: Modifier = Modifier,
    text: String,
    singleLine: Boolean,
    maxLines: Int,
    textColor: Color,
    textBold: Boolean,
    textAlign: TextAlign?,
    textStyle: TextStyle
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        color = textColor,
        style = textStyle,
        fontWeight = if (textBold) {
            FontWeight.Bold
        } else {
            null
        },
        maxLines = if (singleLine) {
            1
        } else {
            maxLines
        }
    )
}