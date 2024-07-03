package com.dreamsoftware.fitflextv.ui.screens.training.trainingentities

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dreamsoftware.fitflextv.ui.screens.training.trainingentities.components.ChallengeTabs
import com.dreamsoftware.fitflextv.ui.screens.training.trainingentities.components.RoundedGradientImage
import com.dreamsoftware.fitflextv.ui.screens.training.trainingentities.components.TrainingEntityDetails

@Composable
internal fun TrainingEntityScreenContent(
    state: TrainingEntityUiState,
    onClickStart: () -> Unit,
) {
    var isChallengeTabsVisible by remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxSize()) {
        AnimatedVisibility(visible = !isChallengeTabsVisible) {
            Box(contentAlignment = Alignment.BottomStart) {
                RoundedGradientImage(imageUrl = state.imageUrl)
                TrainingEntityDetails(
                    state = state,
                    onClickStart = onClickStart,
                    onClickSecondaryButton = {},
                    onClickChallengesPlan = { isChallengeTabsVisible = true },
                    onClickRoutineFavourite = {}
                )
            }
        }
        AnimatedVisibility(visible = isChallengeTabsVisible) {
            ChallengeTabs(
                state = state,
                onClickBackChallenge = { isChallengeTabsVisible = false },
                onClickCard = {}
            )
        }
    }
}