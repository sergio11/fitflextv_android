package com.dreamsoftware.fitflextv.ui.screens.app

import androidx.lifecycle.viewModelScope
import com.dreamsoftware.fitflextv.ui.core.BaseViewModel
import com.dreamsoftware.fitflextv.ui.core.SideEffect
import com.dreamsoftware.fitflextv.ui.core.UiState
import com.dreamsoftware.fitflextv.utils.AppEvent
import com.dreamsoftware.fitflextv.utils.AppEventBus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val appEventBus: AppEventBus
): BaseViewModel<AppUiState, AppSideEffects>() {

    init {
        observeEvents()
    }

    override fun onGetDefaultState(): AppUiState = AppUiState()

    private fun observeEvents() {
        viewModelScope.launch {
            appEventBus.events.collect { event ->
                when(event) {
                    is AppEvent.ComeFromStandby -> launchSideEffect(AppSideEffects.ComeFromStandby)
                    is AppEvent.SignOff -> launchSideEffect(AppSideEffects.NoSessionActive)
                    is AppEvent.NetworkConnectivityStateChanged ->
                        onNetworkConnectivityChanged(event.newState)
                    AppEvent.GoToStandby -> {}
                }
            }
        }
    }

    private fun onNetworkConnectivityChanged(newState: Boolean) {
        updateState {
            it.copy(hasNetworkConnectivity = newState)
        }
    }
}

data class AppUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val hasNetworkConnectivity: Boolean = true
): UiState<AppUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): AppUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface AppSideEffects: SideEffect {
    data object ComeFromStandby: AppSideEffects
    data object NoSessionActive: AppSideEffects
}