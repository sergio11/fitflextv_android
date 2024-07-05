package com.dreamsoftware.fitflextv.ui.core.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Button
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.MaterialTheme

private val DEFAULT_BUTTON_LARGE_WIDTH = 200.dp
private val DEFAULT_BUTTON_LARGE_HEIGHT = 55.dp
private val DEFAULT_BUTTON_MEDIUM_WIDTH = 150.dp
private val DEFAULT_BUTTON_MEDIUM_HEIGHT = 40.dp
private val DEFAULT_BUTTON_SMALL_WIDTH = 100.dp
private val DEFAULT_BUTTON_SMALL_HEIGHT = 35.dp

enum class CommonButtonTypeEnum {
    SMALL,
    MEDIUM,
    LARGE
}

enum class CommonButtonStyleTypeEnum {
    NORMAL,
    INVERSE,
    TRANSPARENT
}

@Composable
fun CommonButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    type: CommonButtonTypeEnum = CommonButtonTypeEnum.MEDIUM,
    style: CommonButtonStyleTypeEnum = CommonButtonStyleTypeEnum.NORMAL,
    enableBorder: Boolean = true,
    buttonShape: Shape = RoundedCornerShape(percent = 20),
    text: String? = null,
    textRes: Int? = null,
    onClick: () -> Unit,
) {
    with(MaterialTheme.colorScheme) {
        Button(
            onClick = onClick,
            modifier = modifier.then(
                Modifier
                    .width(
                        when (type) {
                            CommonButtonTypeEnum.SMALL -> DEFAULT_BUTTON_SMALL_WIDTH
                            CommonButtonTypeEnum.MEDIUM -> DEFAULT_BUTTON_MEDIUM_WIDTH
                            CommonButtonTypeEnum.LARGE -> DEFAULT_BUTTON_LARGE_WIDTH
                        }
                    )
                    .height(
                        when (type) {
                            CommonButtonTypeEnum.SMALL -> DEFAULT_BUTTON_SMALL_HEIGHT
                            CommonButtonTypeEnum.MEDIUM -> DEFAULT_BUTTON_MEDIUM_HEIGHT
                            CommonButtonTypeEnum.LARGE -> DEFAULT_BUTTON_LARGE_HEIGHT
                        }
                    )
                    .clip(buttonShape)
                    .border(
                        width = if (enableBorder) {
                            2.dp
                        } else {
                            0.dp
                        },
                        color = if(enableBorder){
                            when(style) {
                                CommonButtonStyleTypeEnum.NORMAL -> onPrimaryContainer
                                CommonButtonStyleTypeEnum.INVERSE -> onSecondaryContainer
                                CommonButtonStyleTypeEnum.TRANSPARENT -> onTertiaryContainer
                            }
                        } else {
                            Color.Transparent
                        },
                        shape = buttonShape
                    )
            ),
            enabled = enabled,
            shape = ButtonDefaults.shape(shape = buttonShape),
            colors = ButtonDefaults.colors(
                containerColor = when(style) {
                    CommonButtonStyleTypeEnum.NORMAL -> if (enableBorder) {
                        primaryContainer.copy(alpha = 0.8f)
                    } else {
                        primaryContainer
                    }
                    CommonButtonStyleTypeEnum.INVERSE -> if (enableBorder) {
                        secondaryContainer.copy(alpha = 0.8f)
                    } else {
                        secondaryContainer
                    }
                    CommonButtonStyleTypeEnum.TRANSPARENT -> Color.Transparent
                },
                contentColor = when(style) {
                    CommonButtonStyleTypeEnum.NORMAL -> onPrimaryContainer
                    CommonButtonStyleTypeEnum.INVERSE -> onSecondaryContainer
                    CommonButtonStyleTypeEnum.TRANSPARENT -> onTertiaryContainer
                },
                focusedContainerColor = when(style) {
                    CommonButtonStyleTypeEnum.NORMAL -> primary.copy(alpha = 0.8f)
                    CommonButtonStyleTypeEnum.INVERSE ->  primary.copy(alpha = 0.8f)
                    CommonButtonStyleTypeEnum.TRANSPARENT -> primary.copy(alpha = 0.8f)
                }
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CommonText(
                    type = when(type) {
                        CommonButtonTypeEnum.SMALL -> CommonTextTypeEnum.BODY_SMALL
                        CommonButtonTypeEnum.MEDIUM -> CommonTextTypeEnum.BODY_MEDIUM
                        CommonButtonTypeEnum.LARGE -> CommonTextTypeEnum.BODY_LARGE
                    },
                    titleText = text,
                    titleRes = textRes,
                    textColor = when(style) {
                        CommonButtonStyleTypeEnum.NORMAL -> onPrimaryContainer
                        CommonButtonStyleTypeEnum.INVERSE -> onSecondaryContainer
                        CommonButtonStyleTypeEnum.TRANSPARENT -> onTertiaryContainer
                    },
                    textAlign = TextAlign.Center,
                    textBold = true
                )
            }
        }
    }
}