package com.dreamsoftware.fitflextv.ui.screens.profiles.selector

import com.dreamsoftware.fitflextv.domain.model.ProfileBO
import com.dreamsoftware.fitflextv.domain.usecase.GetProfilesUseCase
import com.dreamsoftware.fitflextv.domain.usecase.SelectProfileUseCase
import com.dreamsoftware.fitflextv.ui.core.BaseViewModel
import com.dreamsoftware.fitflextv.ui.core.SideEffect
import com.dreamsoftware.fitflextv.ui.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileSelectorViewModel @Inject constructor(
    private val getProfilesUseCase: GetProfilesUseCase,
    private val selectProfileUseCase: SelectProfileUseCase,
): BaseViewModel<ProfileSelectorUiState, ProfileSelectorSideEffects>(), ProfileSelectorScreenActionListener {

    override fun onGetDefaultState(): ProfileSelectorUiState = ProfileSelectorUiState()

    fun loadProfiles() {
        executeUseCase(
            useCase = getProfilesUseCase,
            onSuccess = ::onLoadProfileSuccessfully
        )
    }

    override fun onProfileSelected(profile: ProfileBO) {
        val isProfileLocked = profile.isSecured
        updateState {
            it.copy(profileSelected = profile)
        }
        if(isProfileLocked) {
            onProfileLocked(profile.uuid)
        } else {
            selectProfile(profile)
        }
    }

    override fun onAddProfilePressed() {
        launchSideEffect(ProfileSelectorSideEffects.AddNewProfile)
    }

    override fun onProfileManagementPressed() {
        launchSideEffect(ProfileSelectorSideEffects.ConfigureProfiles)
    }

    private fun onLoadProfileSuccessfully(profiles: List<ProfileBO>) {
        updateState {
            it.copy(profiles = profiles)
        }
    }

    private fun onProfileLocked(profileId: String) {
        launchSideEffect(ProfileSelectorSideEffects.ProfileLocked(profileId))
    }

    private fun onProfileSelected() {
        launchSideEffect(ProfileSelectorSideEffects.ProfileSelected)
    }

    private fun selectProfile(profileBO: ProfileBO) {
        executeUseCaseWithParams(
            useCase = selectProfileUseCase,
            params = SelectProfileUseCase.Params(profileBO),
            onSuccess = {
                onProfileSelected()
            }
        )
    }
}

data class ProfileSelectorUiState(
    override var isLoading: Boolean = false,
    override var errorMessage: String? = null,
    val profiles: List<ProfileBO> = emptyList(),
    val profileSelected: ProfileBO? = null
): UiState<ProfileSelectorUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): ProfileSelectorUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface ProfileSelectorSideEffects: SideEffect {
    data object ProfileSelected: ProfileSelectorSideEffects
    data class ProfileLocked(val profileId: String): ProfileSelectorSideEffects
    data object AddNewProfile: ProfileSelectorSideEffects
    data object ConfigureProfiles: ProfileSelectorSideEffects
}