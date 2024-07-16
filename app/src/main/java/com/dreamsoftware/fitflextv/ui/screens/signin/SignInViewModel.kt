package com.dreamsoftware.fitflextv.ui.screens.signin

import com.dreamsoftware.fitflextv.domain.model.UserDetailBO
import com.dreamsoftware.fitflextv.domain.usecase.SignInUseCase
import com.dreamsoftware.fitflextv.ui.core.BaseViewModel
import com.dreamsoftware.fitflextv.ui.core.SideEffect
import com.dreamsoftware.fitflextv.ui.core.UiState
import com.dreamsoftware.fitflextv.ui.utils.EMPTY
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
): BaseViewModel<SignInUiState, SignInSideEffects>() {

    override fun onGetDefaultState(): SignInUiState = SignInUiState()

    fun onSignIn() {
        with(uiState.value) {
            executeUseCaseWithParams(
                useCase = signInUseCase,
                params = SignInUseCase.Params(email, password),
                onSuccess = ::onSignInSuccessfully,
                onMapExceptionToState = ::onMapExceptionToState
            )
        }
    }

    fun onEmailChanged(newEmail: String) {
        updateState { it.copy(email = newEmail) }
    }

    fun onPasswordChanged(newPassword: String) {
        updateState { it.copy(password = newPassword,) }
    }

    private fun onSignInSuccessfully(userDetail: UserDetailBO) {
        /*launchSideEffect(if(authenticationBO.profilesCount > 0) {
            SignInSideEffects.ProfileSelectionRequired
        } else {
            SignInSideEffects.AuthenticationSuccessfully
        })*/
        launchSideEffect(SignInSideEffects.AuthenticationSuccessfully)
    }

    private fun onMapExceptionToState(ex: Exception, uiState: SignInUiState) =
        uiState.copy(
            errorMessage = null
        )
}

data class SignInUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val email: String = DEMO_USER_EMAIL,
    val emailError: String = String.EMPTY,
    val password: String = DEMO_USER_PASSWORD,
    val passwordError: String = String.EMPTY
): UiState<SignInUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): SignInUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface SignInSideEffects: SideEffect {
    data object AuthenticationSuccessfully: SignInSideEffects
    data object ProfileSelectionRequired: SignInSideEffects
}

private const val DEMO_USER_EMAIL = "ssanchez@yopmail.com"
private const val DEMO_USER_PASSWORD = "PasswOrd!11"