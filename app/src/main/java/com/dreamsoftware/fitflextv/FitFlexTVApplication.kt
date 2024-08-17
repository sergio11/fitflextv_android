package com.dreamsoftware.fitflextv

import android.app.Application
import com.dreamsoftware.fitflextv.utils.IApplicationAware
import com.dreamsoftware.fudge.utils.IFudgeAppEvent
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FitFlexTVApplication : Application(), IApplicationAware


sealed interface AppEvent: IFudgeAppEvent {
    data object GoToStandby : AppEvent
    data object ComeFromStandby : AppEvent
    data object SignOff: AppEvent
    data class NetworkConnectivityStateChanged(val lastState: Boolean, val newState: Boolean):
        AppEvent
}

