package com.dreamsoftware.fitflextv.ui.screens.trainingdetail.components

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Border
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.Icon
import androidx.tv.material3.IconButton
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.domain.model.TrainingTypeEnum
import com.dreamsoftware.fitflextv.ui.screens.trainingdetail.TrainingDetailUiState
import com.dreamsoftware.fitflextv.ui.utils.getStartButtonID
import com.dreamsoftware.fudge.component.FudgeTvButton
import com.dreamsoftware.fudge.component.FudgeTvButtonStyleTypeEnum
import com.dreamsoftware.fudge.component.FudgeTvButtonTypeEnum
import com.dreamsoftware.fudge.component.FudgeTvFocusRequester
import com.dreamsoftware.fudge.component.FudgeTvText
import com.dreamsoftware.fudge.component.FudgeTvTextTypeEnum

@Composable
fun TrainingEntityDetails(
    state: TrainingDetailUiState,
    onStartTrainingClicked: () -> Unit,
    onMoreInfoClicked: () -> Unit,
    onTrainingFavoriteClicked: () -> Unit,
    onChallengesPlanClicked: () -> Unit
) {
    with(state) {
        val descriptionWidth = (LocalConfiguration.current.screenWidthDp / 2).dp
        val isChallenges = trainingType == TrainingTypeEnum.CHALLENGES
        val paddingBottom = when (trainingType) {
            TrainingTypeEnum.CHALLENGES -> 24.dp
            else -> 80.dp
        }
        with(MaterialTheme.colorScheme) {
            FudgeTvFocusRequester { requester ->
                Column(
                    modifier = Modifier.padding(start = 48.dp, bottom = paddingBottom),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        FudgeTvText(
                            titleText = subtitle,
                            type =  FudgeTvTextTypeEnum.BODY_SMALL,
                            textColor = onSurfaceVariant
                        )
                        FudgeTvText(
                            titleText = title,
                            type = FudgeTvTextTypeEnum.HEADLINE_LARGE,
                            textColor = onSurface,
                            textBold = true
                        )
                    }
                    FudgeTvText(
                        modifier = Modifier.width(descriptionWidth),
                        titleText = description,
                        type = FudgeTvTextTypeEnum.BODY_LARGE,
                        maxLines = 2,
                        textColor = onSurfaceVariant
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(48.dp)) {
                        itemsInfo.forEach { item ->
                            TrainingInfo(
                                info = item.info,
                                labelRes = item.labelRes
                            )
                        }
                    }
                    Row(
                        modifier = Modifier.padding(top = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        FudgeTvButton(
                            modifier = Modifier.focusRequester(requester),
                            type = FudgeTvButtonTypeEnum.LARGE,
                            style = FudgeTvButtonStyleTypeEnum.NORMAL,
                            textRes = trainingType.getStartButtonID(),
                            onClick = onStartTrainingClicked
                        )
                        FudgeTvButton(
                            type = FudgeTvButtonTypeEnum.LARGE,
                            style = FudgeTvButtonStyleTypeEnum.INVERSE,
                            textRes = R.string.more_info,
                            onClick = onMoreInfoClicked
                        )
                        FavouriteButton(
                            isFavorite = isFavorite,
                            onClick = onTrainingFavoriteClicked
                        )
                    }
                    if (isChallenges)
                        ChallengesPlanButton(
                            modifier = Modifier.padding(top = 16.dp),
                            subtitle = stringResource(R.string.weekly_plan),
                            iconId = R.drawable.down_arrow_head_icon,
                            onClick = onChallengesPlanClicked
                        )
                }
            }
        }
    }
}

@Composable
private fun TrainingInfo(
    info: String,
    @StringRes labelRes: Int,
) {
    with(MaterialTheme.colorScheme) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            FudgeTvText(
                type = FudgeTvTextTypeEnum.BODY_LARGE,
                titleText = info,
                textBold = true,
                textColor = onBackground
            )
            FudgeTvText(
                type = FudgeTvTextTypeEnum.BODY_SMALL,
                titleRes = labelRes,
                textColor = onSurfaceVariant
            )
        }
    }
}

@Composable
private fun FavouriteButton(isFavorite: Boolean, onClick: () -> Unit) {
    with(MaterialTheme.colorScheme) {
        val interactionSource = remember { MutableInteractionSource() }
        val isFocused by interactionSource.collectIsFocusedAsState()
        IconButton(
            onClick = onClick,
            interactionSource = interactionSource,
            modifier = Modifier.size(50.dp),
            colors = ButtonDefaults.colors(containerColor = Color.Transparent),
            border = ButtonDefaults.border(
                border = Border(
                    BorderStroke(
                        2.dp,
                        if(isFocused) {
                            border
                        } else {
                            onPrimary
                        }
                    )
                )
            )
        ) {
            Icon(
                painter = painterResource(
                    if (isFavorite)
                        R.drawable.favorite
                    else
                        R.drawable.fav_icon
                ),
                tint = if(isFocused) {
                    border
                } else {
                    onPrimary
                },
                contentDescription = null
            )
        }
    }
}