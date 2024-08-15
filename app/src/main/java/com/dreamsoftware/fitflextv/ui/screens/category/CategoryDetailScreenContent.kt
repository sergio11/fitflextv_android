package com.dreamsoftware.fitflextv.ui.screens.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.ui.core.components.CommonCard
import com.dreamsoftware.fitflextv.ui.core.components.CommonFocusRequester
import com.dreamsoftware.fitflextv.ui.core.components.CommonLoadingState
import com.dreamsoftware.fitflextv.ui.core.components.CommonNoContentState
import com.dreamsoftware.fitflextv.ui.core.components.CommonScreenContent
import com.dreamsoftware.fitflextv.ui.core.components.CommonText
import com.dreamsoftware.fitflextv.ui.core.components.CommonTextTypeEnum
import com.dreamsoftware.fitflextv.ui.utils.conditional

@Composable
internal fun CategoryDetailScreenContent(
    uiState: CategoryDetailUiState,
    actionListener: CategoryDetailActionListener
) {
    with(uiState) {
        CommonScreenContent(onErrorAccepted = actionListener::onErrorAccepted) {
            if (isLoading) {
                CommonLoadingState(modifier = Modifier.fillMaxSize())
            } else if(category != null && trainings.isEmpty()) {
                CommonNoContentState(
                    modifier = Modifier.fillMaxSize(),
                    messageRes = R.string.category_detail_no_trainings_available
                )
            } else {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    CommonText(
                        modifier = Modifier.padding(bottom = 8.dp, top = 56.dp, start = 32.dp, end = 32.dp),
                        type = CommonTextTypeEnum.HEADLINE_MEDIUM,
                        titleRes = R.string.category_detail_trainings_title_default.takeUnless { category != null },
                        titleText = category?.let {
                            stringResource(id = R.string.category_detail_trainings_title, it.title)
                        },
                        textBold = true
                    )
                    CommonFocusRequester { focusRequester ->
                        LazyHorizontalGrid(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 24.dp),
                            contentPadding = PaddingValues(horizontal = 46.dp),
                            rows = GridCells.Fixed(2),
                            horizontalArrangement = Arrangement.spacedBy(24.dp),
                            verticalArrangement = Arrangement.spacedBy(24.dp)
                        ) {
                            itemsIndexed(items = trainings, key = { _, item -> item.id }) { idx, item ->
                                CommonCard(modifier = Modifier
                                    .conditional(condition = idx == 0, ifTrue = {
                                        focusRequester(focusRequester)
                                    }),
                                    onClick = {
                                        actionListener.onTrainingProgramOpened(item)
                                    },
                                    imageUrl = item.imageUrl,
                                    title = item.name,
                                    timeText = item.duration,
                                    typeText = item.intensity.value
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}