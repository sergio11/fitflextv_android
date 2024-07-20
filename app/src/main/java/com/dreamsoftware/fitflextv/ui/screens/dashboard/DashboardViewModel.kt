package com.dreamsoftware.fitflextv.ui.screens.dashboard

import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.ui.core.BaseViewModel
import com.dreamsoftware.fitflextv.ui.core.SideEffect
import com.dreamsoftware.fitflextv.ui.core.UiState
import com.dreamsoftware.fitflextv.ui.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor() : BaseViewModel<DashboardUiState, DashboardSideEffects>(), DashboardActionListener {

    override fun onGetDefaultState(): DashboardUiState = DashboardUiState(
        items = listOf(
            NavigationDrawerItemModel(
                nameRes = R.string.dashboard_navigation_drawer_home_item_name,
                iconRes = R.drawable.home,
                screen = Screen.Home
            ),
            NavigationDrawerItemModel(
                nameRes = R.string.dashboard_navigation_drawer_training_item_name,
                iconRes = R.drawable.fitness_center,
                screen = Screen.Training
            ),
            NavigationDrawerItemModel(
                nameRes = R.string.dashboard_navigation_drawer_favorite_item_name,
                iconRes = R.drawable.favorite,
                screen = Screen.Favorite
            ),
            NavigationDrawerItemModel(
                nameRes = R.string.dashboard_navigation_drawer_settings_item_name,
                iconRes = R.drawable.settings,
                screen = Screen.Settings
            )
        )
    )

    override fun onMenuItemSelected(menuItem: NavigationDrawerItemModel) {
        launchSideEffect(DashboardSideEffects.OpenScreen(menuItem.screen))
    }

}

data class DashboardUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val items: List<NavigationDrawerItemModel> = emptyList(),
): UiState<DashboardUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): DashboardUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface DashboardSideEffects: SideEffect {
    data class OpenScreen(val screen: Screen): DashboardSideEffects
}