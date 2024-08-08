package com.dreamsoftware.fitflextv.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.tv.material3.CarouselState
import androidx.tv.material3.ExperimentalTvMaterial3Api
import com.dreamsoftware.fitflextv.domain.model.TrainingTypeEnum
import com.dreamsoftware.fitflextv.ui.core.components.CommonScreen

@OptIn(ExperimentalTvMaterial3Api::class)
val carouselSaver =
    Saver<CarouselState, Int>(save = { it.activeItemIndex }, restore = { CarouselState(it) })

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onOpenTrainingCategory: (String) -> Unit,
    onOpenTrainingProgram: (String, TrainingTypeEnum) -> Unit,
    onGoToSubscriptions: () -> Unit
) {
    val carouselState = rememberSaveable(saver = carouselSaver) { CarouselState(0) }
    CommonScreen(
        viewModel = viewModel,
        onInitialUiState = { HomeUiState() },
        onSideEffect = {
            when(it) {
                is HomeSideEffects.OpenTrainingCategory -> onOpenTrainingCategory(it.categoryId)
                is HomeSideEffects.OpenTrainingProgram -> onOpenTrainingProgram(it.id, it.type )
                HomeSideEffects.NoActivePremiumSubscription -> onGoToSubscriptions()
            }
        },
        onInit = {
            fetchData()
        }
    ) { uiState ->
        HomeScreenContent(
            state = uiState,
            carouselState = carouselState,
            actionListener = viewModel
        )
    }
}

