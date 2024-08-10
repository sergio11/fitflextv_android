package com.dreamsoftware.fitflextv.ui.screens.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.tv.material3.Carousel
import androidx.tv.material3.CarouselDefaults
import androidx.tv.material3.CarouselState
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.ShapeDefaults
import coil.compose.AsyncImage
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.domain.model.ITrainingProgramBO
import com.dreamsoftware.fitflextv.ui.core.components.CommonButton
import com.dreamsoftware.fitflextv.ui.core.components.CommonButtonStyleTypeEnum
import com.dreamsoftware.fitflextv.ui.core.components.CommonButtonTypeEnum
import com.dreamsoftware.fitflextv.ui.core.components.CommonFocusRequester
import com.dreamsoftware.fitflextv.ui.core.components.CommonText
import com.dreamsoftware.fitflextv.ui.core.components.CommonTextTypeEnum
import com.dreamsoftware.fitflextv.ui.theme.shadowCarouselColor
import com.dreamsoftware.fitflextv.ui.utils.conditional
import com.dreamsoftware.fitflextv.ui.utils.shadowBox

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
internal fun FeaturedTrainings(
    trainings: List<ITrainingProgramBO>,
    padding: PaddingValues,
    carouselState: CarouselState,
    onOpenTrainingProgram: (ITrainingProgramBO) -> Unit,
    modifier: Modifier = Modifier,
) {
    var isCarouselFocused by remember { mutableStateOf(false) }
    CommonFocusRequester { focusRequester ->
        Carousel(
            modifier = modifier
                .focusRequester(focusRequester)
                .fillMaxSize()
                .padding(padding)
                .conditional(isCarouselFocused, ifTrue = {
                    shadowBox(
                        color = shadowCarouselColor,
                        blurRadius = 40.dp,
                        offset = DpOffset(0.dp, 8.dp),
                        shape = MaterialTheme.shapes.extraLarge,
                    )
                })
                .border(
                    width = 3.dp,
                    color = MaterialTheme.colorScheme.border.copy(alpha = if (isCarouselFocused) 1f else 0f),
                    shape = MaterialTheme.shapes.extraLarge,
                )
                .clip(MaterialTheme.shapes.extraLarge)
                .onFocusChanged {
                    isCarouselFocused = it.hasFocus
                },
            itemCount = trainings.size,
            carouselState = carouselState,
            carouselIndicator = {
                CarouselIndicator(
                    itemCount = trainings.size,
                    activeItemIndex = carouselState.activeItemIndex
                )
            },
            contentTransformStartToEnd = fadeIn(tween(durationMillis = 1000)).togetherWith(
                fadeOut(tween(durationMillis = 1000))
            ),
            contentTransformEndToStart = fadeIn(tween(durationMillis = 1000)).togetherWith(
                fadeOut(tween(durationMillis = 1000))
            )
        ) { index ->
            Box(modifier = Modifier.fillMaxSize()) {
                val training = trainings[index]
                CarouselItemBackground(
                    modifier = Modifier.fillMaxSize(),
                    trainingProgram = training
                )
                CarouselItemForeground(
                    training = training,
                    isCarouselFocused = isCarouselFocused,
                    onOpenTrainingProgram = { onOpenTrainingProgram(training) },
                    modifier = Modifier.align(Alignment.BottomStart)
                )
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun BoxScope.CarouselIndicator(
    modifier: Modifier = Modifier,
    itemCount: Int,
    activeItemIndex: Int,
) {
    Box(modifier = modifier
        .padding(32.dp)
        .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
        .graphicsLayer {
            clip = true
            shape = ShapeDefaults.ExtraSmall
        }
        .align(Alignment.BottomEnd)) {
        CarouselDefaults.IndicatorRow(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp),
            itemCount = itemCount,
            activeItemIndex = activeItemIndex,
        )
    }
}

@Composable
private fun CarouselItemForeground(
    training: ITrainingProgramBO,
    onOpenTrainingProgram: () -> Unit,
    modifier: Modifier = Modifier,
    isCarouselFocused: Boolean = false
) {
    with(MaterialTheme.colorScheme) {
        Column(
            modifier = modifier
                .padding(start = 34.dp, bottom = 32.dp)
                .width(360.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            CommonText(
                type = CommonTextTypeEnum.LABEL_MEDIUM,
                titleText = training.instructorName,
                singleLine = true,
                textColor = onSurfaceVariant
            )
            CommonText(
                modifier = Modifier.padding(top = 4.dp),
                type = CommonTextTypeEnum.HEADLINE_SMALL,
                titleText = training.name,
                singleLine = true,
                textColor = onSurface
            )
            CommonText(
                modifier = Modifier.padding(top = 12.dp, bottom = 28.dp),
                type = CommonTextTypeEnum.BODY_SMALL,
                titleText = training.description,
                singleLine = true,
                textColor = onSurfaceVariant,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            AnimatedVisibility(visible = isCarouselFocused) {
                CommonButton(
                    type = CommonButtonTypeEnum.LARGE,
                    style = CommonButtonStyleTypeEnum.TRANSPARENT,
                    textRes = R.string.start_session,
                    onClick = onOpenTrainingProgram
                )
            }
        }
    }
}

@Composable
private fun CarouselItemBackground(trainingProgram: ITrainingProgramBO, modifier: Modifier = Modifier) {
    with(MaterialTheme.colorScheme) {
        var sizeCard by remember { mutableStateOf(Size.Zero) }
        AsyncImage(model = trainingProgram.imageUrl,
            contentDescription = stringResource(id = R.string.image, trainingProgram.name),
            modifier = modifier
                .fillMaxSize()
                .aspectRatio(21F / 9F)
                .onGloballyPositioned { coordinates ->
                    sizeCard = coordinates.size.toSize()
                }
                .drawWithContent {
                    drawContent()
                    drawRect(
                        Brush.radialGradient(
                            colors = listOf(
                                primary.copy(alpha = 0.0f),
                                primary.copy(alpha = 0.12f),
                            ),
                            center = Offset(sizeCard.width, -(sizeCard.width * .35F)),
                            radius = sizeCard.width * .75f,
                        )
                    )
                }
                .drawWithContent {
                    drawContent()
                    drawRect(
                        Brush.radialGradient(
                            colors = listOf(
                                surface.copy(alpha = 0.1f),
                                surface,
                            ),
                            center = Offset(sizeCard.width, -(sizeCard.width * .35F)),
                            radius = sizeCard.width * .75f,
                        )
                    )
                },
            contentScale = ContentScale.Crop
        )
    }
}