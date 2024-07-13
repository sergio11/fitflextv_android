package com.dreamsoftware.fitflextv.ui.screens.category

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fitflextv.ui.core.components.CommonScreen

data class CategoryDetailScreenArgs(
    val id: String
)

@Composable
fun CategoryDetailScreen(
    viewModel: CategoryDetailScreenViewModel = hiltViewModel(),
    args: CategoryDetailScreenArgs,
    onBackPressed: () -> Unit
) {
    CommonScreen(
        viewModel = viewModel,
        onBackPressed = onBackPressed,
        onInitialUiState = { CategoryDetailUiState() },
        onInit = {
            fetchData(args.id)
        }
    ) { uiState ->
        CategoryDetailScreenContent(
            uiState = uiState,
            actionListener = viewModel
        )
    }
}