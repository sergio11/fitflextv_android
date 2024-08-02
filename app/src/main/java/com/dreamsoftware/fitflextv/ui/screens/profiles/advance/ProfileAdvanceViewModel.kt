package com.dreamsoftware.fitflextv.ui.screens.profiles.advance

import androidx.annotation.StringRes
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.domain.model.ProfileBO
import com.dreamsoftware.fitflextv.domain.usecase.GetProfileByIdUseCase
import com.dreamsoftware.fitflextv.ui.core.BaseViewModel
import com.dreamsoftware.fitflextv.ui.core.SideEffect
import com.dreamsoftware.fitflextv.ui.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileAdvanceViewModel @Inject constructor(
    private val getProfileByIdUseCase: GetProfileByIdUseCase
): BaseViewModel<ProfileAdvanceUiState, ProfileAdvanceSideEffects>() {

    override fun onGetDefaultState(): ProfileAdvanceUiState = ProfileAdvanceUiState()

    fun load(profileId: String) {
        executeUseCaseWithParams(
            useCase = getProfileByIdUseCase,
            params = GetProfileByIdUseCase.Params(profileId),
            onSuccess = ::onLoadProfileCompleted
        )
    }

    fun onNewTabSelected(newTab: ProfileAdvancedTab) {
        updateState {
            it.copy(tabSelected = newTab)
        }
    }

    private fun onLoadProfileCompleted(profileBO: ProfileBO) {
        updateState {
            it.copy(profile = profileBO)
        }
    }
}

data class ProfileAdvanceUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val profile: ProfileBO? = null,
    val tabs: List<ProfileAdvancedTab> = listOf(
        ProfileAdvancedTab.ChangeSecurePinTab,
        ProfileAdvancedTab.TimeRestrictionsTab
    ),
    val tabSelected: ProfileAdvancedTab = ProfileAdvancedTab.ChangeSecurePinTab
): UiState<ProfileAdvanceUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): ProfileAdvanceUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}


sealed class ProfileAdvancedTab(
    @StringRes val titleRes: Int
) {
    data object ChangeSecurePinTab: ProfileAdvancedTab(
        titleRes = R.string.profiles_advance_change_pin_tab_label_text
    )

    data object TimeRestrictionsTab: ProfileAdvancedTab(
        titleRes = R.string.profiles_advance_time_restrictions_tab_label_text
    )
}


sealed interface ProfileAdvanceSideEffects: SideEffect