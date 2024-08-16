package com.dreamsoftware.fitflextv.ui.screens.profiles.changesecurepin

import com.dreamsoftware.fitflextv.domain.model.ProfileBO
import com.dreamsoftware.fitflextv.domain.usecase.GetProfileByIdUseCase
import com.dreamsoftware.fitflextv.ui.core.BaseViewModel
import com.dreamsoftware.fitflextv.ui.core.SideEffect
import com.dreamsoftware.fitflextv.ui.core.UiState
import com.dreamsoftware.fitflextv.ui.utils.EMPTY
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChangeSecurePinViewModel @Inject constructor(
    private val getProfileByIdUseCase: GetProfileByIdUseCase
): BaseViewModel<ChangeSecurePinUiState, ChangeSecurePinSideEffects>(), ChangeSecurePinActionListener {

    override fun onGetDefaultState(): ChangeSecurePinUiState = ChangeSecurePinUiState()

    fun load(profileId: String) {
        executeUseCaseWithParams(
            useCase = getProfileByIdUseCase,
            params = GetProfileByIdUseCase.Params(profileId),
            onSuccess = ::onLoadProfileCompleted
        )
    }

    private fun onLoadProfileCompleted(profileBO: ProfileBO) {
        updateState {
            it.copy(profile = profileBO)
        }
    }

    override fun onConfirmPressed() {
    }

    override fun onDeleteProfilePressed() {
        uiState.value.profile?.let {
            launchSideEffect(ChangeSecurePinSideEffects.RequestDeleteProfile(it.uuid))
        }
    }

    override fun onCurrentSecurePinChanged(pin: String) {
        updateState { it.copy(currentSecurePin = pin) }
    }

    override fun onNewSecurePinChanged(pin: String) {
        updateState { it.copy(newSecurePin = pin) }
    }
}

data class ChangeSecurePinUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val profile: ProfileBO? = null,
    val currentSecurePin: String = String.EMPTY,
    val currentSecurePinError: String = String.EMPTY,
    val newSecurePin: String = String.EMPTY,
    val newSecurePinError: String = String.EMPTY,
): UiState<ChangeSecurePinUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): ChangeSecurePinUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface ChangeSecurePinSideEffects: SideEffect {
    data class RequestDeleteProfile(val id: String): ChangeSecurePinSideEffects
}