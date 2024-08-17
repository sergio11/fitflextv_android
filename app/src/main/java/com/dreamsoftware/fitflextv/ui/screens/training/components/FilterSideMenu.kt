package com.dreamsoftware.fitflextv.ui.screens.training.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Icon
import androidx.tv.material3.ListItem
import androidx.tv.material3.ListItemDefaults
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.ui.screens.training.TrainingFilterVO
import com.dreamsoftware.fudge.component.FudgeTvButton
import com.dreamsoftware.fudge.component.FudgeTvButtonStyleTypeEnum
import com.dreamsoftware.fudge.component.FudgeTvButtonTypeEnum
import com.dreamsoftware.fudge.component.FudgeTvFocusRequester
import com.dreamsoftware.fudge.component.FudgeTvText
import com.dreamsoftware.fudge.component.FudgeTvTextTypeEnum
import com.dreamsoftware.fudge.utils.conditional

@Composable
internal fun FilterSideMenu(
    onClearFilters: () -> Unit,
    filtrationFields: List<TrainingFilterVO>,
    onFieldClicked: (TrainingFilterVO) -> Unit
) {
    with(MaterialTheme.colorScheme) {
        FudgeTvFocusRequester { focusRequester ->
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
                            titleRes = R.string.filters,
                            textColor = onSurface
                        )
                        FudgeTvButton(
                            type = FudgeTvButtonTypeEnum.SMALL,
                            style = FudgeTvButtonStyleTypeEnum.TRANSPARENT,
                            textRes = R.string.clear,
                            onClick = onClearFilters
                        )
                    }
                }
                itemsIndexed(filtrationFields) { idx, field ->
                    val interactionSource = remember { MutableInteractionSource() }
                    val isFocused by interactionSource.collectIsFocusedAsState()
                    ListItem(
                        modifier = Modifier.conditional(idx == 0, ifTrue = {
                            focusRequester(focusRequester)
                        }),
                        interactionSource = interactionSource,
                        selected = false,
                        onClick = {
                            onFieldClicked(field)
                        },
                        leadingContent = {
                            Icon(
                                painter = painterResource(id = field.icon),
                                modifier = Modifier.size(ListItemDefaults.IconSize),
                                contentDescription = "field icon"
                            )
                        },
                        trailingContent = {
                            Icon(
                                Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                modifier = Modifier.size(ListItemDefaults.IconSize),
                                contentDescription = "back icon"
                            )
                        },
                        headlineContent = {
                            FudgeTvText(
                                type = FudgeTvTextTypeEnum.TITLE_MEDIUM,
                                titleRes = field.title,
                                textColor = if(isFocused) {
                                    surfaceVariant
                                } else {
                                    onSurfaceVariant
                                }
                            )
                        },
                        supportingContent = {
                            FudgeTvText(
                                type = FudgeTvTextTypeEnum.BODY_SMALL,
                                titleText = field.description,
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