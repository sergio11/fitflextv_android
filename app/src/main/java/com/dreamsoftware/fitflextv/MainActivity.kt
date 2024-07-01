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
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Surface
import com.dreamsoftware.fitflextv.presentation.App
import com.dreamsoftware.fitflextv.presentation.theme.FitFlexTVTheme
import com.dreamsoftware.fitflextv.presentation.theme.LocalNavigationProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    @OptIn(ExperimentalTvMaterial3Api::class)
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
                        App(
                            navController = navController,
                            onBackPressed = onBackPressedDispatcher::onBackPressed,
                        )
                    }

                }
            }
        }
    }
}