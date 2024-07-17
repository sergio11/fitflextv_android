package com.dreamsoftware.fitflextv.ui.screens.profiles.management

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fitflextv.ui.core.components.CommonScreen

@Composable
fun ProfilesManagementScreen(
    viewModel: ProfilesManagementViewModel = hiltViewModel(),
    onBackPressed: () -> Unit,
    onGoToEditProfile: (String) -> Unit
) {
    CommonScreen(
        viewModel = viewModel,
        onInit = { loadProfiles() },
        onBackPressed = onBackPressed,
        onInitialUiState = { ProfilesManagementUiState() },
    ) { uiState ->
        ProfilesManagementScreenContent(
            uiState = uiState,
            onCompletePressed = onBackPressed,
            onProfileSelected = { onGoToEditProfile(it.uuid) },
            onErrorAccepted = ::onErrorAccepted
        )
    }
}