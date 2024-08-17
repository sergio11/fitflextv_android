package com.dreamsoftware.fitflextv.ui.screens.splash

import androidx.lifecycle.viewModelScope
import com.dreamsoftware.fitflextv.domain.usecase.HasMultiplesProfilesUseCase
import com.dreamsoftware.fitflextv.domain.usecase.VerifyUserSessionUseCase
import com.dreamsoftware.fudge.core.FudgeTvViewModel
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val verifyUserSessionUseCase: VerifyUserSessionUseCase,
    private val hasMultiplesProfilesUseCase: HasMultiplesProfilesUseCase
): FudgeTvViewModel<SplashUiState, SplashSideEffects>() {

    override fun onGetDefaultState(): SplashUiState = SplashUiState()

    fun verifySession() {
        viewModelScope.launch {
            delay(4000)
            executeUseCase(
                useCase = verifyUserSessionUseCase,
                onSuccess = ::onVerifyUserSessionCompleted,
                onFailed = ::onVerifyUserSessionFailed
            )
        }
    }

    private fun checkProfiles() {
        executeUseCase(
            useCase = hasMultiplesProfilesUseCase,
            onSuccess = ::onCheckProfilesCompleted,
            onFailed = ::onCheckProfilesFailed
        )
    }

    private fun onCheckProfilesCompleted(hasMultipleProfiles: Boolean) {
        launchSideEffect(
            if(hasMultipleProfiles) {
                SplashSideEffects.ProfileSelectionRequired
            } else {
                SplashSideEffects.UserAlreadyAuthenticated
            }
        )
    }

    private fun onCheckProfilesFailed() {
        launchSideEffect(SplashSideEffects.UserNotAuthenticated)
    }

    private fun onVerifyUserSessionCompleted(hasActiveSession: Boolean) {
        if(hasActiveSession) {
            checkProfiles()
        } else {
            launchSideEffect(SplashSideEffects.UserNotAuthenticated)
        }
    }

    private fun onVerifyUserSessionFailed() {
        launchSideEffect(SplashSideEffects.UserNotAuthenticated)
    }
}

data class SplashUiState(
    override var isLoading: Boolean = false,
    override var errorMessage: String? = null,
    val isAuth: Boolean = false
): UiState<SplashUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): SplashUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface SplashSideEffects: SideEffect {
    data object UserAlreadyAuthenticated: SplashSideEffects
    data object ProfileSelectionRequired: SplashSideEffects
    data object UserNotAuthenticated: SplashSideEffects
}