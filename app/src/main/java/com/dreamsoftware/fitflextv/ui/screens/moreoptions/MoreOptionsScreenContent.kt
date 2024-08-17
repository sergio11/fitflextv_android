package com.dreamsoftware.fitflextv.ui.screens.moreoptions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.ui.utils.formatTimeAndTypeTraining
import com.dreamsoftware.fitflextv.ui.utils.getStartButtonID
import com.dreamsoftware.fitflextv.ui.utils.toTrainingType
import com.dreamsoftware.fudge.component.FudgeTvBackRowSchema
import com.dreamsoftware.fudge.component.FudgeTvCardDetails
import com.dreamsoftware.fudge.component.FudgeTvFocusRequester
import com.dreamsoftware.fudge.component.FudgeTvMoreOptionsButton
import com.dreamsoftware.fudge.component.FudgeTvScreenContent

@Composable
internal fun MoreOptionsScreenContent(
    state: MoreOptionsUiState,
    actionListener: MoreOptionsScreenActionListener
) {
    with(state) {
        FudgeTvScreenContent(
            onErrorAccepted = actionListener::onErrorMessageCleared
        ) {
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

                    FudgeTvCardDetails(
                        modifier = Modifier.width(268.dp).constrainAs(trainingDetails) {},
                        title = trainingProgram?.name.orEmpty(),
                        time = trainingProgram.formatTimeAndTypeTraining(),
                        description = trainingProgram?.description.orEmpty(),
                        imageUrl = trainingProgram?.imageUrl.orEmpty()
                    )
                    FudgeTvBackRowSchema(
                        modifier = Modifier.constrainAs(backRowSchema) {
                            top.linkTo(trainingDetails.bottom, margin = 50.dp)
                        },
                        onClickBack = actionListener::onBackPressed
                    )
                    FudgeTvFocusRequester { focusRequester ->
                        FudgeTvMoreOptionsButton(
                            modifier = Modifier
                                .focusRequester(focusRequester)
                                .constrainAs(startButton) {
                                    top.linkTo(trainingDetails.top)
                                    start.linkTo(trainingDetails.end, margin = 164.dp)
                                },
                            textRes = trainingProgram?.toTrainingType()?.getStartButtonID() ?: R.string.start_workout,
                            icon = R.drawable.ic_rounded_play,
                            onClick = actionListener::onTrainingProgramOpened
                        )
                    }

                    FudgeTvMoreOptionsButton(
                        modifier = Modifier.constrainAs(favoritesButton) {
                            top.linkTo(startButton.bottom, margin = 12.dp)
                            start.linkTo(startButton.start)
                        },
                        textRes = if (isFavorite) {
                            R.string.remove_from_favorites
                        } else {
                            R.string.add_to_favorites
                        },
                        icon = if (isFavorite) {
                            R.drawable.favorite
                        } else {
                            R.drawable.ic_outline_favorite
                        },
                        onClick = actionListener::onFavouriteClicked
                    )
                    FudgeTvMoreOptionsButton(
                        modifier = Modifier.constrainAs(moreInfoButton) {
                            top.linkTo(favoritesButton.bottom, margin = 12.dp)
                            start.linkTo(startButton.start)
                        },
                        textRes = R.string.play_training_song_button_text,
                        icon = R.drawable.music_icon,
                        onClick = actionListener::onPlayTrainingSong
                    )
                    FudgeTvMoreOptionsButton(
                        modifier = Modifier.constrainAs(viewInstructorButton) {
                            top.linkTo(moreInfoButton.bottom, margin = 12.dp)
                            start.linkTo(startButton.start)
                        },
                        textRes = R.string.view_instructor,
                        icon = R.drawable.ic_instructor,
                        onClick = actionListener::onOpenInstructorDetail
                    )
                    FudgeTvMoreOptionsButton(
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