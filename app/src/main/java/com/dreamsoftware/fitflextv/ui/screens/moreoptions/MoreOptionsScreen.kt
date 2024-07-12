package com.dreamsoftware.fitflextv.ui.screens.moreoptions

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fitflextv.domain.model.TrainingTypeEnum
import com.dreamsoftware.fitflextv.ui.core.components.CommonScreen


data class MoreOptionsScreenArgs(
    val id: String,
    val type: TrainingTypeEnum
)

@Composable
fun MoreOptionsScreen(
    viewModel: MoreOptionsViewModel = hiltViewModel(),
    args: MoreOptionsScreenArgs,
    onBackPressed: () -> Unit,
    onStartClick: () -> Unit,
    onFavouriteClick: () -> Unit,
) {
    CommonScreen(
        viewModel = viewModel,
        onBackPressed = onBackPressed,
        onInitialUiState = { MoreOptionsUiState() },
        onSideEffect = {

        },
        onInit = {
            with(args) {
                fetchData(id = id, type = type)
            }
        }
    ) { uiState ->
        MoreOptionsScreenContent(
            state = uiState,
            onBackPressed = onBackPressed,
            onStartClick = onStartClick,
            onFavouriteClick = onFavouriteClick
        )
    }
}

