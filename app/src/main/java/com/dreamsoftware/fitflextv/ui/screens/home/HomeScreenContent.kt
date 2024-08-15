package com.dreamsoftware.fitflextv.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.tv.material3.CarouselState
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.fitflextv.ui.core.components.CommonScreenContent
import com.dreamsoftware.fitflextv.ui.screens.home.components.Categories
import com.dreamsoftware.fitflextv.ui.screens.home.components.FeaturedTrainings
import com.dreamsoftware.fitflextv.ui.screens.home.components.TrainingsRecommended

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
internal fun HomeScreenContent(
    state: HomeUiState,
    carouselState: CarouselState,
    actionListener: HomeScreenActionListener
) {
    with(state) {
        CommonScreenContent(onErrorAccepted = actionListener::onErrorAccepted) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .semantics { contentDescription = "Home Screen" },
                verticalArrangement = Arrangement.spacedBy(40.dp),
                contentPadding = PaddingValues(vertical = 40.dp)
            ) {
                item {
                    FeaturedTrainings(
                        trainings = featuredTrainings,
                        padding = PaddingValues(horizontal = 32.dp),
                        onOpenTrainingProgram = actionListener::onOpenTrainingProgram,
                        carouselState = carouselState,
                        modifier = Modifier
                            .height(340.dp)
                            .fillMaxWidth()
                    )
                }
                item {
                    Categories(
                        categories = categories,
                        onClick = actionListener::onCategorySelected
                    )
                }
                item {
                    TrainingsRecommended(
                        state = state.recommended,
                        onClick = actionListener::onOpenTrainingProgram
                    )
                }
            }
        }
    }
}