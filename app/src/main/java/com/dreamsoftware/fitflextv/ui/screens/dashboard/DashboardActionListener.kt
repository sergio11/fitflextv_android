package com.dreamsoftware.fitflextv.ui.screens.dashboard

import com.dreamsoftware.fudge.core.IFudgeScreenActionListener

interface DashboardActionListener: IFudgeScreenActionListener {
    fun onMenuItemSelected(menuItem: NavigationDrawerItemModel)
}