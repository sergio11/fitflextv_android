package com.dreamsoftware.fitflextv.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class AppEventBus {

    private val coroutineScope = CoroutineScope(Dispatchers.Default)
    private val _events = MutableSharedFlow<AppEvent>()
    val events = _events.asSharedFlow()

    fun send(event: AppEvent) {
        coroutineScope.launch {
            _events.emit(event)
        }
    }
}

sealed interface AppEvent {
    data object GoToStandby : AppEvent
    data object ComeFromStandby : AppEvent
    data object SignOff: AppEvent
    data class NetworkConnectivityStateChanged(val lastState: Boolean, val newState: Boolean):
        AppEvent
}