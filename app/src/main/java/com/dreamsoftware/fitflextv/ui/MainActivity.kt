package com.dreamsoftware.fitflextv.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.dreamsoftware.fitflextv.ui.receiver.ScreenStateReceiver
import com.dreamsoftware.fitflextv.ui.screens.app.AppScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var screenStateReceiver: ScreenStateReceiver

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screenStateReceiver = ScreenStateReceiver.register(this)
        setContent {
            AppScreen()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ScreenStateReceiver.unregister(this, screenStateReceiver)
    }
}