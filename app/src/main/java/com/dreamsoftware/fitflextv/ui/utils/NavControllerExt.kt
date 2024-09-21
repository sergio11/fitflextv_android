package com.dreamsoftware.fitflextv.ui.utils

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

fun NavController.navigateSingleTopTo(
    route: String,
    clearPreviousRoute: Boolean = true
) =
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id,
        ) {
            saveState = true
            inclusive = clearPreviousRoute
        }
        restoreState = true
        launchSingleTop = true
    }