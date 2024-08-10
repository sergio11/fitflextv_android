package com.dreamsoftware.fitflextv.ui.screens.trainingdetail.components

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.domain.model.TrainingTypeEnum
import com.dreamsoftware.fitflextv.ui.core.components.CommonButton
import com.dreamsoftware.fitflextv.ui.core.components.CommonButtonStyleTypeEnum
import com.dreamsoftware.fitflextv.ui.core.components.CommonButtonTypeEnum
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
        with(MaterialTheme.colorScheme) {
            CommonFocusRequester { requester ->
                Column(
                    modifier = Modifier.padding(start = 48.dp, bottom = paddingBottom),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        CommonText(
                            titleText = subtitle,
                            type =  CommonTextTypeEnum.BODY_SMALL,
                            textColor = onSurfaceVariant
                        )
                        CommonText(
                            titleText = title,
                            type = CommonTextTypeEnum.HEADLINE_LARGE,
                            textColor = onSurface,
                            textBold = true
                        )
                    }
                    CommonText(
                        modifier = Modifier.width(descriptionWidth),
                        titleText = description,
                        type = CommonTextTypeEnum.BODY_LARGE,
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
                        CommonButton(
                            modifier = Modifier.focusRequester(requester),
                            type = CommonButtonTypeEnum.LARGE,
                            style = CommonButtonStyleTypeEnum.NORMAL,
                            textRes = trainingType.getStartButtonID(),
                            onClick = onStartTrainingClicked
                        )
                        CommonButton(
                            type = CommonButtonTypeEnum.LARGE,
                            style = CommonButtonStyleTypeEnum.INVERSE,
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
            CommonText(
                type = CommonTextTypeEnum.BODY_LARGE,
                titleText = info,
                textBold = true,
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
        modifier = Modifier.size(50.dp),
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