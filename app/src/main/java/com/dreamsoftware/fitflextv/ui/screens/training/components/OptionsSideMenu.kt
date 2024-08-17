package com.dreamsoftware.fitflextv.ui.screens.training.components

import androidx.annotation.StringRes
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ListItem
import androidx.tv.material3.ListItemDefaults
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.RadioButton
import androidx.tv.material3.RadioButtonDefaults
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fudge.component.FudgeTvButton
import com.dreamsoftware.fudge.component.FudgeTvButtonStyleTypeEnum
import com.dreamsoftware.fudge.component.FudgeTvButtonTypeEnum
import com.dreamsoftware.fudge.component.FudgeTvFocusRequester
import com.dreamsoftware.fudge.component.FudgeTvText
import com.dreamsoftware.fudge.component.FudgeTvTextTypeEnum
import com.dreamsoftware.fudge.utils.conditional

@Composable
fun OptionsSideMenu(
    onDismissSideMenu: () -> Unit,
    @StringRes titleRes: Int,
    items: List<String>,
    selectedIndex: Int,
    onSelectedItem: (currentIndex: Int) -> Unit,
) {
    with(MaterialTheme.colorScheme) {
        FudgeTvFocusRequester(selectedIndex) { focusRequester ->
            LazyColumn(
                contentPadding = PaddingValues(vertical = 32.dp, horizontal = 20.dp)
            ) {
                item {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 24.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        FudgeTvText(
                            type = FudgeTvTextTypeEnum.HEADLINE_SMALL,
                            titleRes = titleRes,
                            textColor = onSurface
                        )
                        FudgeTvButton(
                            type = FudgeTvButtonTypeEnum.SMALL,
                            style = FudgeTvButtonStyleTypeEnum.TRANSPARENT,
                            textRes = R.string.reset,
                            onClick = onDismissSideMenu
                        )
                    }
                }
                items(items.size) { index ->
                    val interactionSource = remember { MutableInteractionSource() }
                    val isFocused by interactionSource.collectIsFocusedAsState()
                    ListItem(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .conditional(condition = index == selectedIndex, ifTrue = {
                                focusRequester(focusRequester)
                            }),
                        interactionSource = interactionSource,
                        selected = false,
                        onClick = { onSelectedItem(index) },
                        trailingContent = {
                            RadioButton(
                                selected = selectedIndex == index,
                                onClick = { onSelectedItem(index) },
                                modifier = Modifier.size(ListItemDefaults.IconSizeDense),
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = if(isFocused) {
                                        surfaceVariant
                                    } else {
                                        onSurfaceVariant
                                    },
                                    unselectedColor = if(isFocused) {
                                        surfaceVariant
                                    } else {
                                        onSurfaceVariant
                                    }
                                )
                            )
                        },
                        headlineContent = {
                            FudgeTvText(
                                type = FudgeTvTextTypeEnum.TITLE_MEDIUM,
                                titleText = items[index],
                                textColor = if(isFocused) {
                                    surfaceVariant
                                } else {
                                    onSurfaceVariant
                                }
                            )
                        },
                        colors = ListItemDefaults.colors(
                            containerColor = Color.Transparent,
                            contentColor = onSurfaceVariant,
                            focusedContentColor = surfaceVariant
                        ),
                        shape = ListItemDefaults.shape(shape = RoundedCornerShape(16.dp))
                    )
                }
            }
        }
    }
}