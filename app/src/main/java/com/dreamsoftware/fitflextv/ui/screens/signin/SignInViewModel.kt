package com.dreamsoftware.fitflextv.ui.screens.signin

import com.dreamsoftware.fitflextv.di.SignInScreenErrorMapper
import com.dreamsoftware.fitflextv.domain.model.UserDetailBO
import com.dreamsoftware.fitflextv.domain.usecase.SignInUseCase
import com.dreamsoftware.fitflextv.ui.core.IErrorMapper
import com.dreamsoftware.fitflextv.ui.utils.EMPTY
import com.dreamsoftware.fudge.core.FudgeViewModel
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    @SignInScreenErrorMapper private val errorMapper: IErrorMapper
): FudgeViewModel<SignInUiState, SignInSideEffects>() {

    private companion object {
        const val DEFAULT_PROFILES_COUNT = 1
    }

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
        launchSideEffect(if(userDetail.profilesCount > DEFAULT_PROFILES_COUNT) {
            SignInSideEffects.ProfileSelectionRequired
        } else {
            SignInSideEffects.AuthenticationSuccessfully
        })
    }

    private fun onMapExceptionToState(ex: Exception, uiState: SignInUiState) =
        uiState.copy(
            isLoading = false,
            errorMessage = errorMapper.mapToMessage(ex)
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
private const val DEMO_USER_PASSWORD = "Passw0rd!11"