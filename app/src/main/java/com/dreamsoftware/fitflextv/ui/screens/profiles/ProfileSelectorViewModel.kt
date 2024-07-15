package com.dreamsoftware.fitflextv.ui.screens.profiles

import com.dreamsoftware.fitflextv.domain.model.ProfileBO
import com.dreamsoftware.fitflextv.domain.usecase.GetUserProfilesUseCase
import com.dreamsoftware.fitflextv.ui.core.BaseViewModel
import com.dreamsoftware.fitflextv.ui.core.SideEffect
import com.dreamsoftware.fitflextv.ui.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.annotation.concurrent.Immutable
import javax.inject.Inject

@HiltViewModel
class ProfileSelectorViewModel @Inject constructor(
    private val getUserProfilesUseCase: GetUserProfilesUseCase
) : BaseViewModel<ProfileSelectorUiState, ProfileSelectorSideEffects>() {

    override fun onGetDefaultState(): ProfileSelectorUiState = ProfileSelectorUiState()

    fun getUserProfiles() {
        executeUseCase(
            useCase = getUserProfilesUseCase,
            onSuccess = ::onGetUserProfilesSuccessfully,
            onMapExceptionToState = ::onMapExceptionToState
        )
    }

    private fun onGetUserProfilesSuccessfully(profiles: List<ProfileBO>) {
        updateState { it.copy(profiles = profiles.map { profile -> profile.toProfileUiState() } ) }
    }

    private fun onMapExceptionToState(ex: Exception, uiState: ProfileSelectorUiState) =
        uiState.copy(
            errorMessage = null
        )

    private fun ProfileBO.toProfileUiState() =
        ProfileVO(
            id = id,
            name = name,
            avatar = avatar
        )
}

data class ProfileSelectorUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val lastFocusedItemId: String = "",
    val profileItemAlreadyFocused: Boolean = false,
    val profiles: List<ProfileVO> = emptyList(),
): UiState<ProfileSelectorUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): ProfileSelectorUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

@Immutable
data class ProfileVO(
    val id: String,
    val name: String,
    val avatar: String,
)

sealed interface ProfileSelectorSideEffects: SideEffect