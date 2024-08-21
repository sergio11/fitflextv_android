package com.dreamsoftware.fitflextv.ui.screens.trainingdetail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.focusRestorer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.ui.screens.trainingdetail.TrainingDetailUiState
import com.dreamsoftware.fudge.component.FudgeTvCard
import com.dreamsoftware.fudge.component.FudgeTvRoundedGradientImage
import com.dreamsoftware.fudge.component.FudgeTvTabRow

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun ChallengeTabs(
    state: TrainingDetailUiState,
    onClickBackChallenge: () -> Unit,
    onClickCard: (Int) -> Unit,

) {
    with(MaterialTheme.colorScheme) {
        var selectedTabIndex by remember { mutableIntStateOf(0) }
        Box(modifier = Modifier.fillMaxSize()) {
            FudgeTvRoundedGradientImage(
                modifier = Modifier.alpha(.5f),
                imageUrl = state.imageUrl
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp),
            ) {
                ChallengesPlanButton(
                    modifier = Modifier.padding(start = 48.dp),
                    subtitle = stringResource(R.string.challenge_details),
                    iconId = R.drawable.up_arrow_head_icon,
                    onClick = onClickBackChallenge
                )
                Text(
                    modifier = Modifier.padding(top = 52.dp, start = 48.dp),
                    text = stringResource(R.string.weekly_plan),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium,
                    color = onSurface,
                )

                FudgeTvTabRow(
                    modifier = Modifier
                        .padding(top = 24.dp, start = 48.dp),
                    tabs = state.tabs,
                    enableRowIndicator = true,
                    selectedTabIndex = selectedTabIndex,
                    focusTabIndex = selectedTabIndex,
                    onClick = { selectedTabIndex = it },
                    onFocus = { selectedTabIndex = it },
                )
                LazyRow(
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .focusRestorer(),
                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                    contentPadding = PaddingValues(horizontal = 48.dp)
                ) {
                    itemsIndexed(
                        state.weaklyPlans[selectedTabIndex].getOrDefault(
                            state.tabs[selectedTabIndex],
                            listOf()
                        )
                    ) { index, item ->
                        FudgeTvCard(
                            modifier = Modifier.width(196.dp),
                            imageUrl = item.imageUrl,
                            title = item.title,
                            timeText = item.time,
                            typeText = item.typeText,
                            onClick = { onClickCard(index) }
                        )
                    }
                }
            }
        }
    }
}