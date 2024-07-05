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
import androidx.compose.foundation.layout.width
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
import androidx.tv.foundation.lazy.grid.TvGridCells
import androidx.tv.foundation.lazy.grid.TvLazyHorizontalGrid
import androidx.tv.foundation.lazy.grid.items
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.domain.model.FavWorkout
import com.dreamsoftware.fitflextv.ui.core.components.CommonCardWithIntensity
import com.dreamsoftware.fitflextv.ui.core.components.CommonFocusRequester
import com.dreamsoftware.fitflextv.ui.core.components.CommonOutLinedButtonWithLeadingIcon
import com.dreamsoftware.fitflextv.ui.core.components.CommonText
import com.dreamsoftware.fitflextv.ui.core.components.CommonTextTypeEnum
import com.dreamsoftware.fitflextv.ui.theme.onSurface
import com.dreamsoftware.fitflextv.ui.theme.popupShadow
import com.dreamsoftware.fitflextv.ui.theme.surfaceContainerHigh
import com.dreamsoftware.fitflextv.ui.theme.surfaceVariant
import com.dreamsoftware.fitflextv.ui.utils.shadowBox

@Composable
internal fun FavoritesScreenContent(
    modifier: Modifier = Modifier,
    state: FavoritesUiState,
    onStartWorkout: (id: String) -> Unit,
    onRemoveWorkout: (id: String) -> Unit,
    onWorkoutSelect: (FavWorkout) -> Unit,
    onDismissRequest: () -> Unit,
) {
    with(state) {
        if (isLoading) {
            Loading(modifier = Modifier.fillMaxSize())
        } else if (!errorMessage.isNullOrBlank()) {
            Error(modifier = Modifier.fillMaxSize())
        } else {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                CommonText(
                    modifier = Modifier.padding(bottom = 8.dp, top = 56.dp, start = 32.dp, end = 32.dp),
                    type = CommonTextTypeEnum.HEADLINE_MEDIUM,
                    titleRes = R.string.favorites_screen_title,
                    textBold = true
                )
                TvLazyHorizontalGrid(
                    contentPadding = PaddingValues(horizontal = 46.dp),
                    rows = TvGridCells.Fixed(2),
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp)
                ) {
                    items(items = favoritesWorkouts, key = { it.id }) { item ->
                        CommonCardWithIntensity(modifier = Modifier
                            .width(196.dp)
                            .padding(horizontal = 12.dp),
                            imageUrl = item.image,
                            title = item.name,
                            timeText = item.duration,
                            typeText = "Intensity",
                            intensityLevel = item.intensity,
                            onClick = {
                                onWorkoutSelect(item)
                            })
                    }
                }
                AnimatedVisibility(
                    visible = selectedWorkoutItem != null,
                    enter = fadeIn(
                        animationSpec = tween(300)
                    ),
                    exit = fadeOut(
                        animationSpec = tween(300)
                    ),
                ) {
                    selectedWorkoutItem?.let {
                        WorkoutDetailsPopup(
                            workout = it,
                            onStartWorkout = onStartWorkout,
                            onRemoveWorkout = onRemoveWorkout,
                            onBackPressed = onDismissRequest
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun WorkoutDetailsPopup(
    workout: FavWorkout,
    onStartWorkout: (id: String) -> Unit,
    onRemoveWorkout: (id: String) -> Unit,
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
                    model = workout.image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(surfaceVariant.copy(alpha = 0.35f))
                        .padding(horizontal = 24.dp), horizontalAlignment = Alignment.Start
                ) {
                    Spacer(modifier = Modifier.fillMaxHeight(0.45f))
                    CommonText(
                        modifier = Modifier.padding(bottom = 8.dp),
                        type = CommonTextTypeEnum.HEADLINE_SMALL,
                        textColor = onSurface,
                        textAlign = TextAlign.Justify,
                        titleText = workout.name,
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
                            titleText = "${workout.duration} | Intensity ",
                            textColor = onSurface,
                            overflow = TextOverflow.Ellipsis,
                            softWrap = true,
                            maxLines = 4
                        )
                        repeat(workout.intensity) { Text(text = "•") }
                    }
                    CommonText(
                        titleText = workout.description,
                        modifier = Modifier.padding(bottom = 28.dp),
                        type = CommonTextTypeEnum.BODY_SMALL,
                        textColor = Color.LightGray,
                        overflow = TextOverflow.Ellipsis,
                        softWrap = true,
                        maxLines = 4
                    )
                    CommonOutLinedButtonWithLeadingIcon(
                        text = "Start",
                        icon = R.drawable.play_icon,
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusRequester)
                            .padding(bottom = 12.dp)
                    ) {
                        onStartWorkout(workout.id)
                    }
                    CommonOutLinedButtonWithLeadingIcon(
                        text = "Remove from favorites",
                        icon = R.drawable.icon_remove,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 24.dp)
                    ) {
                        onRemoveWorkout(workout.id)
                    }
                }
            }
        }
    }
}

@Composable
private fun Loading(modifier: Modifier = Modifier) {
    Text(text = "Loading...", modifier = modifier, textAlign = TextAlign.Center)
}

@Composable
private fun Error(modifier: Modifier = Modifier) {
    Text(text = "Wops, something went wrong.", modifier = modifier, textAlign = TextAlign.Center)
}