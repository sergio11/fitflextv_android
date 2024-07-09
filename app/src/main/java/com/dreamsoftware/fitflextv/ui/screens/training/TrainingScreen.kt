package com.dreamsoftware.fitflextv.ui.screens.training

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fitflextv.ui.core.components.CommonScreen

@Composable
fun TrainingScreen(
    viewModel: TrainingViewModel = hiltViewModel(),
    onClickItem: (id: String) -> Unit
) {
    CommonScreen(
        viewModel = viewModel,
        onInitialUiState = { TrainingUiState() },
        onSideEffect = {

        },
        onInit = {
            fetchData()
        }
    ) { uiState ->
        TrainingScreenContent(
            state = uiState,
            onClickFilter = viewModel::onFilterClicked,
            onDismissSideMenu = viewModel::onDismissSideMenu,
            onSelectedItem = viewModel::onSelectedSortedItem,
            onClickSortBy = viewModel::onSortedClicked,
            onChangeSelectedTab = viewModel::onChangeSelectedTab,
            onChangeFocusTab = viewModel::onChangeFocusTab,
            onClickItem = onClickItem
        )
    }
}


