package com.dreamsoftware.fitflextv.ui.screens.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val state = viewModel.settingsUIState.collectAsState()

    val selectedItem = remember { mutableStateOf<SettingsItemUIState?>(null) }

    SettingsScreenContent(
        state = state.value,
        selectedItem = selectedItem,
        updateSetting = viewModel::updateSetting
    )
}

