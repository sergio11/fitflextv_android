package com.dreamsoftware.fitflextv.ui.screens.trainingdetail.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
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
import androidx.tv.material3.OutlinedButton
import androidx.tv.material3.Text
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.ui.core.components.CommonFocusRequester
import com.dreamsoftware.fitflextv.ui.core.components.CommonText
import com.dreamsoftware.fitflextv.ui.core.components.CommonTextTypeEnum
import com.dreamsoftware.fitflextv.ui.screens.trainingdetail.TrainingDetailUiState

@Composable
fun TrainingEntityDetails(
    state: TrainingDetailUiState,
    onClickStart: () -> Unit,
    onClickSecondaryButton: () -> Unit,
    onClickRoutineFavourite: () -> Unit,
    onClickChallengesPlan: () -> Unit
) {
    val descriptionWidth = (LocalConfiguration.current.screenWidthDp / 2).dp
    val isChallenges = state.contentType == TrainingDetailUiState.ContentType.CHALLENGES
    val isRoutine = state.contentType == TrainingDetailUiState.ContentType.ROUTINE
    val paddingBottom = when (state.contentType) {
        TrainingDetailUiState.ContentType.CHALLENGES -> 24.dp
        else -> 80.dp
    }
    CommonFocusRequester { requester ->
        Column(
            modifier = Modifier.padding(start = 48.dp, bottom = paddingBottom),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                CommonText(
                    titleText = state.subtitle,
                    type =  CommonTextTypeEnum.BODY_SMALL,
                    textColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
                CommonText(
                    titleText = state.title,
                    type = CommonTextTypeEnum.HEADLINE_LARGE,
                    textColor = MaterialTheme.colorScheme.onSurface
                )
            }
            CommonText(
                modifier = Modifier.width(descriptionWidth),
                titleText = state.description,
                type = CommonTextTypeEnum.BODY_LARGE,
                maxLines = 2,
                textColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Row(horizontalArrangement = Arrangement.spacedBy(48.dp)) {
                state.itemsInfo.forEach { item ->
                    TrainingInfo(
                        info = item.info,
                        label = item.label
                    )
                }
            }
            Row(
                modifier = Modifier.padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                TrainingDetailsButton(
                    modifier = Modifier.focusRequester(requester),
                    iconId = R.drawable.play_icon,
                    textId = state.contentType.getStartButtonID(),
                    onClick = onClickStart
                )
                if (state.contentType.isSecondaryButtonVisible())
                    TrainingDetailsButton(
                        iconId = state.contentType.getSecondaryButtonIcon(),
                        textId = state.contentType.getSecondaryButtonID(),
                        onClick = onClickSecondaryButton
                    )
                if (isRoutine)
                    RoutineFavouriteButton(
                        isFavorite = state.isFavorite,
                        onClick = onClickRoutineFavourite
                    )
            }
            if (isChallenges)
                ChallengesPlanButton(
                    modifier = Modifier.padding(top = 16.dp),
                    subtitle = stringResource(R.string.weekly_plan),
                    iconId = R.drawable.down_arrow_head_icon,
                    onClick = onClickChallengesPlan
                )
        }
    }
}

@Composable
private fun TrainingDetailsButton(
    modifier: Modifier = Modifier,
    iconId: Int,
    textId: Int,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Icon(
                painter = painterResource(iconId),
                contentDescription = null,
            )
            Text(
                text = stringResource(textId)
            )
        }
    }
}

@Composable
private fun TrainingInfo(
    info: String,
    label: String,
) {
    with(MaterialTheme.colorScheme) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CommonText(
                type = CommonTextTypeEnum.BODY_LARGE,
                titleText = info,
                textColor = onBackground
            )
            CommonText(
                type = CommonTextTypeEnum.BODY_SMALL,
                titleText = label,
                textColor = onSurfaceVariant
            )
        }
    }
}

@Composable
private fun RoutineFavouriteButton(isFavorite: Boolean, onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        colors = ButtonDefaults.colors(containerColor = Color.Transparent),
        border = ButtonDefaults.border(
            border = Border(
                BorderStroke(
                    2.dp,
                    MaterialTheme.colorScheme.border
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
            ), contentDescription = null
        )
    }
}