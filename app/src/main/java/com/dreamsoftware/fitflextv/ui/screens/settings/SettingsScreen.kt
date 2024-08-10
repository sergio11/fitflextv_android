package com.dreamsoftware.fitflextv.ui.screens.settings

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fitflextv.ui.core.components.CommonScreen

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    onGoToSubscriptions: () -> Unit,
    onBackPressed: () -> Unit,
) {
    CommonScreen(
        viewModel = viewModel,
        onBackPressed = onBackPressed,
        onSideEffect = {
            when(it) {
                SettingsSideEffects.OpenSubscriptions -> onGoToSubscriptions()
            }
        },
        onInitialUiState = { SettingsUiState() },
        onInit = {
            fetchData()
        }
    ) { uiState ->
        SettingsScreenContent(
            uiState = uiState,
            actionListener = viewModel
        )
    }
}

