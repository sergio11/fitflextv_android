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
import com.dreamsoftware.fitflextv.ui.utils.formatTimeAndIntensityTraining
import com.dreamsoftware.fudge.component.FudgeTvCard
import com.dreamsoftware.fudge.component.FudgeTvFocusRequester
import com.dreamsoftware.fudge.component.FudgeTvLoadingState
import com.dreamsoftware.fudge.component.FudgeTvNoContentState
import com.dreamsoftware.fudge.component.FudgeTvScreenContent
import com.dreamsoftware.fudge.component.FudgeTvText
import com.dreamsoftware.fudge.component.FudgeTvTextTypeEnum
import com.dreamsoftware.fudge.utils.conditional

@Composable
internal fun CategoryDetailScreenContent(
    uiState: CategoryDetailUiState,
    actionListener: CategoryDetailActionListener
) {
    with(uiState) {
        FudgeTvScreenContent(onErrorAccepted = actionListener::onErrorMessageCleared) {
            if (isLoading) {
                FudgeTvLoadingState(modifier = Modifier.fillMaxSize())
            } else if(category != null && trainings.isEmpty()) {
                FudgeTvNoContentState(
                    modifier = Modifier.fillMaxSize(),
                    messageRes = R.string.category_detail_no_trainings_available
                )
            } else {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    FudgeTvText(
                        modifier = Modifier.padding(bottom = 8.dp, top = 56.dp, start = 32.dp, end = 32.dp),
                        type = FudgeTvTextTypeEnum.HEADLINE_MEDIUM,
                        titleRes = R.string.category_detail_trainings_title_default.takeUnless { category != null },
                        titleText = category?.let {
                            stringResource(id = R.string.category_detail_trainings_title, it.title)
                        },
                        textBold = true
                    )
                    FudgeTvFocusRequester { focusRequester ->
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
                                FudgeTvCard(modifier = Modifier
                                    .conditional(condition = idx == 0, ifTrue = {
                                        focusRequester(focusRequester)
                                    }),
                                    onClick = {
                                        actionListener.onTrainingProgramOpened(item)
                                    },
                                    imageUrl = item.imageUrl,
                                    title = item.name,
                                    subtitle = item.formatTimeAndIntensityTraining(showLevel = false),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}