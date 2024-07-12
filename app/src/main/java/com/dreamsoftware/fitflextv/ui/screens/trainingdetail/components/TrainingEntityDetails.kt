package com.dreamsoftware.fitflextv.ui.screens.trainingdetail.components

import androidx.annotation.StringRes
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
import com.dreamsoftware.fitflextv.domain.model.TrainingTypeEnum
import com.dreamsoftware.fitflextv.ui.core.components.CommonFocusRequester
import com.dreamsoftware.fitflextv.ui.core.components.CommonText
import com.dreamsoftware.fitflextv.ui.core.components.CommonTextTypeEnum
import com.dreamsoftware.fitflextv.ui.screens.trainingdetail.TrainingDetailUiState
import com.dreamsoftware.fitflextv.ui.utils.getStartButtonID

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
        CommonFocusRequester { requester ->
            Column(
                modifier = Modifier.padding(start = 48.dp, bottom = paddingBottom),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    CommonText(
                        titleText = subtitle,
                        type =  CommonTextTypeEnum.BODY_SMALL,
                        textColor = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    CommonText(
                        titleText = title,
                        type = CommonTextTypeEnum.HEADLINE_LARGE,
                        textColor = MaterialTheme.colorScheme.onSurface
                    )
                }
                CommonText(
                    modifier = Modifier.width(descriptionWidth),
                    titleText = description,
                    type = CommonTextTypeEnum.BODY_LARGE,
                    maxLines = 2,
                    textColor = MaterialTheme.colorScheme.onSurfaceVariant
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
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    TrainingDetailsButton(
                        modifier = Modifier.focusRequester(requester),
                        iconId = R.drawable.play_icon,
                        textId = trainingType.getStartButtonID(),
                        onClick = onStartTrainingClicked
                    )
                    TrainingDetailsButton(
                        iconId = R.drawable.ic_info,
                        textId = R.string.more_info,
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
    @StringRes labelRes: Int,
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
                titleRes = labelRes,
                textColor = onSurfaceVariant
            )
        }
    }
}

@Composable
private fun FavouriteButton(isFavorite: Boolean, onClick: () -> Unit) {
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