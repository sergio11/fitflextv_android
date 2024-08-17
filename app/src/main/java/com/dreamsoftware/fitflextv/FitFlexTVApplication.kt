package com.dreamsoftware.fitflextv

import android.app.Application
import com.dreamsoftware.fudge.utils.IFudgeAppEvent
import com.dreamsoftware.fudge.utils.IFudgeTvApplicationAware
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FitFlexTVApplication : Application(), IFudgeTvApplicationAware


sealed interface AppEvent: IFudgeAppEvent {
    data object GoToStandby : AppEvent
    data object ComeFromStandby : AppEvent
    data object SignOff: AppEvent
    data class NetworkConnectivityStateChanged(val lastState: Boolean, val newState: Boolean):
        AppEvent
}

