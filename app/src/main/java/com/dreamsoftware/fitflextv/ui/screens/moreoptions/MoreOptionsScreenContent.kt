package com.dreamsoftware.fitflextv.ui.screens.moreoptions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.ui.core.components.CommonBackRowSchema
import com.dreamsoftware.fitflextv.ui.core.components.CommonCardDetails
import com.dreamsoftware.fitflextv.ui.core.components.CommonFocusRequester
import com.dreamsoftware.fitflextv.ui.core.components.CommonMoreOptionsButton
import com.dreamsoftware.fitflextv.ui.utils.formatTimeAndTypeTraining

@Composable
internal fun MoreOptionsScreenContent(
    state: MoreOptionsUiState,
    onBackPressed: () -> Unit,
    onStartClick: () -> Unit,
    onFavouriteClick: () -> Unit,
) {
    with(state) {
        CommonFocusRequester { focusRequester ->
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                ConstraintLayout {
                    val (
                        trainingDetails,
                        backRowSchema,
                        startButton,
                        favoritesButton,
                        moreInfoButton,
                        viewInstructorButton,
                        shareButton
                    ) = createRefs()

                    CommonCardDetails(
                        modifier = Modifier.constrainAs(trainingDetails) {},
                        title = trainingDetailsBO?.title.orEmpty(),
                        time = trainingDetailsBO.formatTimeAndTypeTraining(),
                        description = trainingDetailsBO?.description.orEmpty()
                    )
                    CommonBackRowSchema(
                        modifier = Modifier.constrainAs(backRowSchema) {
                            top.linkTo(trainingDetails.bottom, margin = 50.dp)
                        },
                        onClickBack = onBackPressed
                    )
                    CommonMoreOptionsButton(
                        modifier = Modifier.focusRequester(focusRequester).constrainAs(startButton) {
                            top.linkTo(trainingDetails.top)
                            start.linkTo(trainingDetails.end, margin = 164.dp)
                        },
                        textRes = R.string.start_workout,
                        icon = R.drawable.ic_rounded_play,
                        onClick = onStartClick
                    )
                    CommonMoreOptionsButton(
                        modifier = Modifier.constrainAs(favoritesButton) {
                            top.linkTo(startButton.bottom, margin = 12.dp)
                            start.linkTo(startButton.start)
                        },
                        textRes = R.string.add_to_favorites,
                        icon = R.drawable.ic_outline_favorite,
                        onClick = onFavouriteClick
                    )
                    CommonMoreOptionsButton(
                        modifier = Modifier.constrainAs(moreInfoButton) {
                            top.linkTo(favoritesButton.bottom, margin = 12.dp)
                            start.linkTo(startButton.start)
                        },
                        textRes = R.string.more_info,
                        icon = R.drawable.ic_info
                    )
                    CommonMoreOptionsButton(
                        modifier = Modifier.constrainAs(viewInstructorButton) {
                            top.linkTo(moreInfoButton.bottom, margin = 12.dp)
                            start.linkTo(startButton.start)
                        },
                        textRes = R.string.view_instructor,
                        icon = R.drawable.ic_instructor
                    )
                    CommonMoreOptionsButton(
                        modifier = Modifier.constrainAs(shareButton) {
                            top.linkTo(viewInstructorButton.bottom, margin = 12.dp)
                            start.linkTo(startButton.start)
                        },
                        textRes = R.string.share,
                        icon = R.drawable.ic_share
                    )
                }
            }
        }
    }
}