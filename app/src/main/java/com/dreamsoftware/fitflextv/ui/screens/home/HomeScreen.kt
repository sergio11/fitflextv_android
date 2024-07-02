package com.dreamsoftware.fitflextv.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.tv.material3.CarouselState
import androidx.tv.material3.ExperimentalTvMaterial3Api

@OptIn(ExperimentalTvMaterial3Api::class)
val carouselSaver =
    Saver<CarouselState, Int>(save = { it.activeItemIndex }, restore = { CarouselState(it) })

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onStartSessionCLick: (id: String) -> Unit,
    onCardClick: (id: String) -> Unit,
) {
    val state by viewModel.state.collectAsState()
    val carouselState = rememberSaveable(saver = carouselSaver) { CarouselState(0) }

    HomeScreenContent(
        state = state,
        carouselState = carouselState,
        onClick = { id ->
            onStartSessionCLick(id)
        },
        onCardClick = { id ->
            onCardClick(id)
        }
    )
}

