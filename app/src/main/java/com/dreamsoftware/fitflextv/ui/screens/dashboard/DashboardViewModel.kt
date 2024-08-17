package com.dreamsoftware.fitflextv.ui.screens.dashboard

import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.domain.model.AvatarTypeEnum
import com.dreamsoftware.fitflextv.domain.model.ProfileBO
import com.dreamsoftware.fitflextv.domain.usecase.GetProfileSelectedUseCase
import com.dreamsoftware.fitflextv.ui.navigation.Screen
import com.dreamsoftware.fitflextv.ui.utils.toDrawableResource
import com.dreamsoftware.fudge.core.FudgeTvViewModel
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getProfileSelectedUseCase: GetProfileSelectedUseCase
) : FudgeTvViewModel<DashboardUiState, DashboardSideEffects>(), DashboardActionListener {

    fun fetchData() {
        executeUseCase(
            useCase = getProfileSelectedUseCase,
            onSuccess = ::onGetSelectedProfileSuccessfully
        )
    }

    override fun onGetDefaultState(): DashboardUiState = DashboardUiState(
        items = onBuildNavigationDrawerMenuItems()
    )

    override fun onMenuItemSelected(menuItem: NavigationDrawerItemModel) {
        launchSideEffect(DashboardSideEffects.OpenScreen(menuItem.screen))
    }

    private fun onGetSelectedProfileSuccessfully(profileBO: ProfileBO) {
        updateState { it.copy(items = onBuildNavigationDrawerMenuItems(profileBO)) }
    }

    private fun onBuildNavigationDrawerMenuItems(currentProfile: ProfileBO? = null) = listOf(
        NavigationDrawerItemModel(
            nameRes = if(currentProfile == null) {
                R.string.dashboard_navigation_drawer_profile_item_name
            } else {
                null
            },
            name = currentProfile?.alias,
            imageRes = (currentProfile?.avatarType ?: AvatarTypeEnum.BOY).toDrawableResource(),
            screen = Screen.Profiles,
            isIcon = false
        ),
        NavigationDrawerItemModel(
            nameRes = R.string.dashboard_navigation_drawer_home_item_name,
            imageRes = R.drawable.home,
            screen = Screen.Home
        ),
        NavigationDrawerItemModel(
            nameRes = R.string.dashboard_navigation_drawer_training_item_name,
            imageRes = R.drawable.fitness_center,
            screen = Screen.Training
        ),
        NavigationDrawerItemModel(
            nameRes = R.string.dashboard_navigation_drawer_favorite_item_name,
            imageRes = R.drawable.favorite,
            screen = Screen.Favorite
        ),
        NavigationDrawerItemModel(
            nameRes = R.string.dashboard_navigation_drawer_settings_item_name,
            imageRes = R.drawable.settings,
            screen = Screen.Settings
        )
    )

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