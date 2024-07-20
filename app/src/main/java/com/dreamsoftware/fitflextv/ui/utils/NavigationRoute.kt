package com.dreamsoftware.fitflextv.ui.utils

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dreamsoftware.fitflextv.ui.navigation.Screen
import com.dreamsoftware.fitflextv.ui.screens.dashboard.DashboardScreen

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun NavGraphBuilder.navigationDrawerGraph(
    onNavigateToProfiles: () -> Unit
) {
    composable(route = Screen.Dashboard.route) {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        DashboardScreen(
            navController = navController,
            currentDestination = navBackStackEntry?.destination,
            onNavigateToScreen = { screen ->
                if(screen is Screen.Profiles) {
                    onNavigateToProfiles()
                } else {
                    navController.navigateSingleTopTo(screen.route)
                }
            }
        )
    }
}