package com.dreamsoftware.fitflextv

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.navigation.compose.rememberNavController
import androidx.tv.material3.Surface
import com.dreamsoftware.fitflextv.ui.navigation.AppNavHost
import com.dreamsoftware.fitflextv.ui.theme.FitFlexTVTheme
import com.dreamsoftware.fitflextv.ui.theme.LocalNavigationProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitFlexTVTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    shape = RectangleShape,
                ) {
                    val navController = rememberNavController()
                    CompositionLocalProvider(LocalNavigationProvider provides navController) {
                        AppNavHost(
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}