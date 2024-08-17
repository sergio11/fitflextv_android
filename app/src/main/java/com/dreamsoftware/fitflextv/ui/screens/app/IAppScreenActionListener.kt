package com.dreamsoftware.fitflextv.ui.screens.app

import com.dreamsoftware.fudge.core.IFudgeScreenActionListener

interface IAppScreenActionListener: IFudgeScreenActionListener {
    fun onOpenSettingsPressed()
    fun onRestartAppPressed()
}