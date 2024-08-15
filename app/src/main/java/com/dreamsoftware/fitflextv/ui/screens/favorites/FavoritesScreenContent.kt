package com.dreamsoftware.fitflextv.ui.screens.favorites

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.domain.model.ITrainingProgramBO
import com.dreamsoftware.fitflextv.ui.core.components.CommonButton
import com.dreamsoftware.fitflextv.ui.core.components.CommonButtonStyleTypeEnum
import com.dreamsoftware.fitflextv.ui.core.components.CommonButtonTypeEnum
import com.dreamsoftware.fitflextv.ui.core.components.CommonCard
import com.dreamsoftware.fitflextv.ui.core.components.CommonFocusRequester
import com.dreamsoftware.fitflextv.ui.core.components.CommonLoadingState
import com.dreamsoftware.fitflextv.ui.core.components.CommonNoContentState
import com.dreamsoftware.fitflextv.ui.core.components.CommonScreenContent
import com.dreamsoftware.fitflextv.ui.core.components.CommonText
import com.dreamsoftware.fitflextv.ui.core.components.CommonTextTypeEnum
import com.dreamsoftware.fitflextv.ui.theme.onSurface
import com.dreamsoftware.fitflextv.ui.theme.popupShadow
import com.dreamsoftware.fitflextv.ui.theme.surfaceContainerHigh
import com.dreamsoftware.fitflextv.ui.theme.surfaceVariant
import com.dreamsoftware.fitflextv.ui.utils.conditional
import com.dreamsoftware.fitflextv.ui.utils.getStartButtonID
import com.dreamsoftware.fitflextv.ui.utils.shadowBox
import com.dreamsoftware.fitflextv.ui.utils.toTrainingType

@Composable
internal fun FavoritesScreenContent(
    uiState: FavoritesUiState,
    actionListener: FavoritesScreenActionListener
) {
    with(uiState) {
        CommonScreenContent(onErrorAccepted = actionListener::onErrorAccepted) {
            if (isLoading) {
                CommonLoadingState(modifier = Modifier.fillMaxSize())
            } else if(favoritesTrainings.isEmpty()) {
                CommonNoContentState(
                    modifier = Modifier.fillMaxSize(),
                    messageRes = R.string.favorites_not_workout_available
                )
            }else {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    CommonText(
                        modifier = Modifier.padding(bottom = 8.dp, top = 56.dp, start = 32.dp, end = 32.dp),
                        type = CommonTextTypeEnum.HEADLINE_MEDIUM,
                        titleRes = R.string.favorites_screen_title,
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
                            itemsIndexed(items = favoritesTrainings, key = { _, item -> item.id }) { idx, item ->
                                CommonCard(modifier = Modifier
                                    .conditional(condition = idx == 0, ifTrue = {
                                        focusRequester(focusRequester)
                                    }),
                                    imageUrl = item.imageUrl,
                                    title = item.name,
                                    timeText = item.duration,
                                    typeText = item.intensity.level,
                                    onClick = {
                                        actionListener.onTrainingProgramSelected(item)
                                    })
                            }
                        }
                    }
                    AnimatedVisibility(
                        visible = trainingProgramSelected != null,
                        enter = fadeIn(
                            animationSpec = tween(300)
                        ),
                        exit = fadeOut(
                            animationSpec = tween(300)
                        ),
                    ) {
                        trainingProgramSelected?.let {
                            TrainingProgramDetailsPopup(
                                trainingProgram = it,
                                onStartTrainingProgram = actionListener::onTrainingProgramStarted,
                                onRemoveFromFavorites = actionListener::onTrainingProgramRemovedFromFavorites,
                                onBackPressed = actionListener::onDismissRequest
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TrainingProgramDetailsPopup(
    trainingProgram: ITrainingProgramBO,
    onStartTrainingProgram: (id: String) -> Unit,
    onRemoveFromFavorites: (id: String) -> Unit,
    onBackPressed: () -> Unit
) {
    Dialog(onDismissRequest = onBackPressed) {
        CommonFocusRequester { focusRequester ->
            Box(
                modifier = Modifier
                    .background(surfaceContainerHigh, RoundedCornerShape(16.dp))
                    .fillMaxWidth(0.75f)
                    .fillMaxHeight(0.8f)
                    .shadowBox(
                        color = popupShadow,
                        blurRadius = 40.dp,
                        offset = DpOffset(0.dp, 8.dp)
                    )
                    .clip(RoundedCornerShape(16.dp))
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f)
                        .alpha(0.88f),
                    model = trainingProgram.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(surfaceVariant.copy(alpha = 0.35f))
                        .padding(horizontal = 24.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Spacer(modifier = Modifier.fillMaxHeight(0.35f))
                    CommonText(
                        modifier = Modifier.padding(bottom = 8.dp),
                        type = CommonTextTypeEnum.HEADLINE_SMALL,
                        textColor = onSurface,
                        textAlign = TextAlign.Justify,
                        titleText = trainingProgram.name,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        CommonText(
                            modifier = Modifier,
                            type = CommonTextTypeEnum.LABEL_MEDIUM,
                            titleText = "${trainingProgram.duration} | ${trainingProgram.intensity.value} ",
                            textColor = onSurface,
                            overflow = TextOverflow.Ellipsis,
                            softWrap = true,
                            maxLines = 4
                        )
                    }
                    CommonText(
                        titleText = trainingProgram.description,
                        modifier = Modifier.padding(bottom = 28.dp),
                        type = CommonTextTypeEnum.BODY_SMALL,
                        textColor = Color.LightGray,
                        overflow = TextOverflow.Ellipsis,
                        softWrap = true,
                        maxLines = 4
                    )
                    CommonButton(
                        modifier = Modifier
                            .focusRequester(focusRequester)
                            .fillMaxWidth()
                            .padding(bottom = 12.dp),
                        type = CommonButtonTypeEnum.MEDIUM,
                        style = CommonButtonStyleTypeEnum.NORMAL,
                        textRes = trainingProgram.toTrainingType().getStartButtonID()
                    ) {
                        onStartTrainingProgram(trainingProgram.id)
                    }
                    CommonButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp),
                        type = CommonButtonTypeEnum.MEDIUM,
                        style = CommonButtonStyleTypeEnum.INVERSE,
                        textRes = R.string.remove_from_favorites
                    ) {
                        onRemoveFromFavorites(trainingProgram.id)
                    }
                }
            }
        }
    }
}