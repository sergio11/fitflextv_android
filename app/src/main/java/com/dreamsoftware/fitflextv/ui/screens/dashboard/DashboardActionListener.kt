package com.dreamsoftware.fitflextv.ui.screens.dashboard

import com.dreamsoftware.fudge.core.IFudgeTvScreenActionListener

interface DashboardActionListener: IFudgeTvScreenActionListener {
    fun onMenuItemSelected(menuItem: NavigationDrawerItemModel)
}