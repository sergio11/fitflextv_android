package com.dreamsoftware.fitflextv.ui.screens.profileSelector

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusRequester
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fitflextv.ui.screens.Screens
import com.dreamsoftware.fitflextv.ui.theme.LocalNavigationProvider
import com.dreamsoftware.fitflextv.ui.utils.navigateTo

@Composable
fun ProfileSelectorScreen(
    onSignInClick: () -> Unit,
) {
    val viewModel: ProfileSelectorViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    val profileFocusRequester = remember { FocusRequester() }
    val navController = LocalNavigationProvider.current

    ProfileSelectorContent(
        state = state,
        profileFocusRequester = profileFocusRequester,
        onSignInClick = { onSignInClick() },
        onProfileSelectedClick = {
            navController.navigateTo(Screens.Dashboard)
        },
    )
}



