package com.dreamsoftware.fitflextv.ui.screens.profiles.management

import com.dreamsoftware.fitflextv.domain.model.ProfileBO
import com.dreamsoftware.fitflextv.domain.usecase.GetProfilesUseCase
import com.dreamsoftware.fitflextv.ui.core.BaseViewModel
import com.dreamsoftware.fitflextv.ui.core.SideEffect
import com.dreamsoftware.fitflextv.ui.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfilesManagementViewModel @Inject constructor(
    private val getProfilesUseCase: GetProfilesUseCase,
):
    BaseViewModel<ProfilesManagementUiState, ProfilesManagementSideEffects>() {

    override fun onGetDefaultState(): ProfilesManagementUiState = ProfilesManagementUiState()

    fun loadProfiles() {
        executeUseCase(
            useCase = getProfilesUseCase,
            onSuccess = ::onLoadProfileSuccessfully
        )
    }

    private fun onLoadProfileSuccessfully(profiles: List<ProfileBO>) {
        updateState {
            it.copy(profiles = profiles)
        }
    }
}

data class ProfilesManagementUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val profiles: List<ProfileBO> = emptyList(),
): UiState<ProfilesManagementUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): ProfilesManagementUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface ProfilesManagementSideEffects: SideEffect