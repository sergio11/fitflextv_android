package com.dreamsoftware.fitflextv.ui.screens.settings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
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
import com.dreamsoftware.fitflextv.ui.core.components.CommonFocusRequester
import com.dreamsoftware.fitflextv.ui.core.components.CommonText
import com.dreamsoftware.fitflextv.ui.core.components.CommonTextTypeEnum
import com.dreamsoftware.fitflextv.ui.theme.surface
import com.dreamsoftware.fitflextv.ui.theme.surfaceContainerHigh
import com.dreamsoftware.fitflextv.ui.utils.conditional

@Composable
fun SettingsScreenContent(
    uiState: SettingsUiState,
    actionListener: SettingsScreenActionListener
) {
    with(uiState) {
        with(MaterialTheme.colorScheme) {
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
                    CommonFocusRequester { focusRequester ->
                        TvLazyColumn(
                            modifier = Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(32.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            itemsIndexed(settingList) { idx, item ->
                                when (item) {
                                    is ISettingItemVO.SettingHeaderVO -> {
                                        CommonText(
                                            titleRes = item.titleRes,
                                            type = CommonTextTypeEnum.BODY_SMALL,
                                            textColor = onSurface,
                                        )
                                    }

                                    is ISettingItemVO.SettingValueVO -> {
                                        SettingsItem(
                                            modifier = Modifier.conditional(idx == 0, ifTrue = {
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
                        SettingsDetail(item = it, actionListener = actionListener)
                    }
                }
            }
        }
    }
}

@Composable
private fun SettingsItem(
    modifier: Modifier = Modifier,
    item: ISettingItemVO.SettingValueVO,
    actionListener: SettingsScreenActionListener
) {
    ListItem(
        modifier = modifier,
        selected = false,
        onClick = {
            actionListener.onSettingItemSelected(item)
        },
        leadingContent = {
            CommonText(
                type = CommonTextTypeEnum.TITLE_MEDIUM,
                titleRes = item.titleRes
            )
        },
        trailingContent = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CommonText(
                    type = CommonTextTypeEnum.TITLE_MEDIUM,
                    titleText = item.value
                )
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
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            focusedContentColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        shape = ListItemDefaults.shape(shape = RoundedCornerShape(8.dp)),
    )
}

@Composable
private fun SubSettingItem(
    item: ISettingItemVO.SettingValueVO,
    actionListener: SettingsScreenActionListener
) {
    item.possibleValues.forEach { subSetting ->
        ListItem(
            onClick = {
                actionListener.onSettingValueChanged(subSetting)
            },
            selected = item.value == subSetting,
            leadingContent = {
                CommonText(
                    titleText = subSetting,
                    type = CommonTextTypeEnum.TITLE_MEDIUM,
                )
            },
            trailingContent = {
                if (item.value == subSetting) {
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
                contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                focusedContentColor = MaterialTheme.colorScheme.surfaceVariant,
                selectedContainerColor = surface
            ),
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Composable
private fun SettingsDetail(
    item: ISettingItemVO.SettingValueVO,
    actionListener: SettingsScreenActionListener
) {
    Column(
        Modifier
            .background(Color(0xFF0B0F0E))
            .fillMaxHeight(),
        horizontalAlignment = Alignment.Start
    ) {
        CommonText(
            modifier = Modifier.padding(top = 64.dp, start = 32.dp),
            type = CommonTextTypeEnum.TITLE_LARGE,
            titleRes = item.titleRes,
            textColor = MaterialTheme.colorScheme.onSurface
        )
        TvLazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(32.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(listOf(item)) {
                SubSettingItem(
                    item = item,
                    actionListener = actionListener
                )
            }
        }
    }
}