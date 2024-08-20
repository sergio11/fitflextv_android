package com.dreamsoftware.fitflextv.ui.screens.signup

import com.dreamsoftware.fitflextv.di.SignUpScreenErrorMapper
import com.dreamsoftware.fitflextv.domain.usecase.SignUpUseCase
import com.dreamsoftware.fitflextv.ui.utils.EMPTY
import com.dreamsoftware.fudge.core.FudgeTvViewModel
import com.dreamsoftware.fudge.core.IFudgeTvErrorMapper
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    @SignUpScreenErrorMapper private val errorMapper: IFudgeTvErrorMapper,
): FudgeTvViewModel<SignUpUiState, SignUpSideEffects>(), SignUpScreenActionListener {

    override fun onGetDefaultState(): SignUpUiState = SignUpUiState()

    override fun onUsernameChanged(newUsername: String) {
        updateState { it.copy(username = newUsername) }
    }

    override fun onFirstNameChanged(newFirstName: String) {
        updateState { it.copy(firstName = newFirstName) }
    }

    override fun onLastNameChanged(newLastName: String) {
        updateState { it.copy(lastName = newLastName) }
    }

    override fun onEmailChanged(newEmail: String) {
        updateState { it.copy(email = newEmail) }
    }

    override fun onPasswordChanged(newPassword: String) {
        updateState { it.copy(password = newPassword) }
    }

    override fun onRepeatPasswordChanged(newRepeatPassword: String) {
        updateState { it.copy(repeatPassword = newRepeatPassword,) }
    }

    override fun onSigUpPressed() {
        with(uiState.value) {
            executeUseCaseWithParams(
                useCase = signUpUseCase,
                params = SignUpUseCase.Params(
                    username = username,
                    repeatPassword = repeatPassword,
                    password = password,
                    email = email,
                    firstName = firstName,
                    lastName = lastName
                ),
                onSuccess = {
                    onSigUpSuccessfully()
                },
                onMapExceptionToState = ::onMapExceptionToState
            )
        }
    }

    override fun onCancelPressed() {
        launchSideEffect(SignUpSideEffects.RegisterCancelled)
    }

    private fun onSigUpSuccessfully() {
        launchSideEffect(SignUpSideEffects.RegisteredSuccessfully)
    }

    private fun onMapExceptionToState(ex: Exception, uiState: SignUpUiState) =
        uiState.copy(
            isLoading = false,
            errorMessage = errorMapper.mapToMessage(ex)
        )
}

data class SignUpUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val username: String = String.EMPTY,
    val usernameError: String = String.EMPTY,
    val email: String = String.EMPTY,
    val emailError: String = String.EMPTY,
    val password: String = String.EMPTY,
    val passwordError: String = String.EMPTY,
    val repeatPassword: String = String.EMPTY,
    val repeatPasswordError: String = String.EMPTY,
    val firstName: String = String.EMPTY,
    val firstNameError: String = String.EMPTY,
    val lastName: String = String.EMPTY,
    val lastNameError: String = String.EMPTY,
): UiState<SignUpUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): SignUpUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface SignUpSideEffects: SideEffect {
    data object RegisteredSuccessfully: SignUpSideEffects
    data object RegisterCancelled: SignUpSideEffects
}