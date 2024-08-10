package com.dreamsoftware.fitflextv

import android.app.Application
import com.dreamsoftware.fitflextv.utils.IApplicationAware
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FitFlexTVApplication : Application(), IApplicationAware
