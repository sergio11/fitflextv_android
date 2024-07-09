package com.dreamsoftware.fitflextv.ui.utils

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dreamsoftware.fitflextv.ui.navigation.Screens
import com.dreamsoftware.fitflextv.ui.screens.dashboard.DashboardScreen

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun NavGraphBuilder.navigationDrawerGraph(
    onNavigateToRoot: (Screens) -> Unit
) {
    composable(route = Screens.Dashboard.route) {

        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        DashboardScreen(
            navController = navController,
            currentDestination = navBackStackEntry?.destination,
            onNavigateTo = { screen ->
                navController.navigateSingleTopTo(screen.route)
            }
        )
    }
}