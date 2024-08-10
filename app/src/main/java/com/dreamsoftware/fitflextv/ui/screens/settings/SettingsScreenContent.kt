package com.dreamsoftware.fitflextv.ui.screens.settings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.foundation.lazy.list.items
import androidx.tv.foundation.lazy.list.itemsIndexed
import androidx.tv.material3.Icon
import androidx.tv.material3.ListItem
import androidx.tv.material3.ListItemDefaults
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.ui.core.components.CommonDialog
import com.dreamsoftware.fitflextv.ui.core.components.CommonFocusRequester
import com.dreamsoftware.fitflextv.ui.core.components.CommonText
import com.dreamsoftware.fitflextv.ui.core.components.CommonTextTypeEnum
import com.dreamsoftware.fitflextv.ui.theme.surfaceContainerHigh
import com.dreamsoftware.fitflextv.ui.utils.conditional

@Composable
fun SettingsScreenContent(
    uiState: SettingsUiState,
    actionListener: SettingsScreenActionListener
) {
    with(uiState) {
        with(MaterialTheme.colorScheme) {
            CommonDialog(
                isVisible = showSignOffDialog,
                titleRes = R.string.settings_confirm_sign_off_dialog_title,
                descriptionRes = R.string.settings_confirm_sign_off_dialog_description,
                onAcceptClicked = actionListener::onSignOffConfirmed,
                onCancelClicked = actionListener::onSignOffCancelled
            )
            Row(
                Modifier
                    .fillMaxSize()
                    .semantics { contentDescription = "Settings Screen" }
            ) {
                Column(
                    Modifier
                        .weight(1f)
                        .background(background),
                    horizontalAlignment = Alignment.Start
                ) {
                    CommonText(
                        titleRes = R.string.settings,
                        type = CommonTextTypeEnum.TITLE_LARGE,
                        textColor = onSurface,
                        modifier = Modifier.padding(top = 64.dp, start = 32.dp)
                    )
                    CommonFocusRequester(uiState) { focusRequester ->
                        TvLazyColumn(
                            modifier = Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(32.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            val firstSettingIdx = settingList.indexOfFirst { it !is ISettingItemVO.SettingHeaderVO }
                            itemsIndexed(settingList) { idx, item ->
                                when (item) {
                                    is ISettingItemVO.SettingHeaderVO -> {
                                        CommonText(
                                            titleRes = item.titleRes,
                                            type = CommonTextTypeEnum.BODY_SMALL,
                                            textColor = onSurface,
                                        )
                                    }
                                    else -> {
                                        SettingsItem(
                                            modifier = Modifier.conditional(firstSettingIdx == idx, ifTrue = {
                                                focusRequester(focusRequester)
                                            }),
                                            item = item,
                                            actionListener = actionListener
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                AnimatedVisibility(
                    visible = settingSelected != null,
                    enter = slideInHorizontally(
                        initialOffsetX = { it },
                        animationSpec = tween(300)
                    ),
                    exit = slideOutHorizontally(
                        targetOffsetX = { it },
                        animationSpec = tween(300)
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    settingSelected?.let {
                        CommonFocusRequester { focusRequester ->
                            SettingsDetail(
                                focusRequester = focusRequester,
                                item = it,
                                actionListener = actionListener
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SettingsItem(
    modifier: Modifier = Modifier,
    item: ISettingItemVO,
    actionListener: SettingsScreenActionListener
) {
    with(MaterialTheme.colorScheme) {
        val interactionSource = remember { MutableInteractionSource() }
        val isFocused by interactionSource.collectIsFocusedAsState()
        val isPressed by interactionSource.collectIsFocusedAsState()
        ListItem(
            modifier = modifier,
            selected = false,
            interactionSource = interactionSource,
            onClick = {
                actionListener.onSettingItemSelected(item)
            },
            leadingContent = {
                CommonText(
                    type = CommonTextTypeEnum.TITLE_MEDIUM,
                    titleRes = item.titleRes,
                    textColor = if (isPressed || isFocused) {
                        surfaceVariant
                    } else {
                        onSurfaceVariant
                    }
                )
            },
            trailingContent = {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if(item is ISettingItemVO.ISettingValueItemVO.SettingMultipleValuesVO) {
                        CommonText(
                            type = CommonTextTypeEnum.TITLE_MEDIUM,
                            titleText = item.value,
                            textColor = if (isPressed || isFocused) {
                                surfaceVariant
                            } else {
                                onSurfaceVariant
                            }
                        )
                    }
                    Icon(
                        Icons.Default.KeyboardArrowRight,
                        modifier = Modifier.size(ListItemDefaults.IconSize),
                        contentDescription = "back icon"
                    )
                }
            },
            headlineContent = {},
            colors = ListItemDefaults.colors(
                containerColor = surfaceContainerHigh.copy(alpha = 0.3f),
                contentColor = onSurfaceVariant,
                focusedContentColor = surfaceVariant
            ),
            shape = ListItemDefaults.shape(shape = RoundedCornerShape(8.dp)),
        )
    }
}

@Composable
private fun SettingMultipleValuesItem(
    item: ISettingItemVO.ISettingValueItemVO.SettingMultipleValuesVO,
    focusRequester: FocusRequester,
    actionListener: SettingsScreenActionListener
) {
    with(MaterialTheme.colorScheme) {
        item.possibleValues.forEach { value ->
            val interactionSource = remember { MutableInteractionSource() }
            val isFocused by interactionSource.collectIsFocusedAsState()
            val isPressed by interactionSource.collectIsFocusedAsState()
            ListItem(
                modifier = Modifier
                    .padding(4.dp)
                    .conditional(item.value == value, ifTrue = {
                        focusRequester(focusRequester)
                    }),
                interactionSource = interactionSource,
                onClick = {
                    actionListener.onSettingValueChanged(value)
                },
                selected = item.value == value,
                leadingContent = {
                    CommonText(
                        titleText = value,
                        type = CommonTextTypeEnum.TITLE_MEDIUM,
                        textColor = if (isPressed || isFocused) {
                            surfaceVariant
                        } else {
                            onSurfaceVariant
                        }
                    )
                },
                trailingContent = {
                    if (item.value == value) {
                        Icon(
                            Icons.Default.CheckCircle,
                            modifier = Modifier.size(ListItemDefaults.IconSize),
                            contentDescription = "selected icon"
                        )
                    }
                },
                headlineContent = {},
                colors = ListItemDefaults.colors(
                    containerColor = surface,
                    contentColor = onSurfaceVariant,
                    focusedContentColor = surfaceVariant,
                    selectedContainerColor = surface
                )
            )
        }
    }
}

@Composable
private fun SettingsDetail(
    focusRequester: FocusRequester,
    item: ISettingItemVO.ISettingValueItemVO,
    actionListener: SettingsScreenActionListener
) {
    with(MaterialTheme.colorScheme) {
        Column(
            Modifier
                .background(surface)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.Start
        ) {
            CommonText(
                modifier = Modifier.padding(top = 64.dp, start = 32.dp),
                type = CommonTextTypeEnum.TITLE_LARGE,
                titleRes = item.titleRes,
                textColor = onSurface
            )
            TvLazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(32.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(listOf(item)) {
                    when(item) {
                        is ISettingItemVO.ISettingValueItemVO.SettingMultipleValuesVO -> SettingMultipleValuesItem(
                            focusRequester = focusRequester,
                            item = item,
                            actionListener = actionListener
                        )
                        is ISettingItemVO.ISettingValueItemVO.SettingSingleValueVO -> {
                            CommonText(
                                titleText = item.value,
                                titleRes = item.valueRes,
                                type = CommonTextTypeEnum.TITLE_MEDIUM
                            )
                        }
                    }
                }
            }
        }
    }
}