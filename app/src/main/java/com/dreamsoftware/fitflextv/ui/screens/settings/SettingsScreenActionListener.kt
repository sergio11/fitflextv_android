package com.dreamsoftware.fitflextv.ui.screens.settings

import com.dreamsoftware.fudge.core.IFudgeScreenActionListener

interface SettingsScreenActionListener: IFudgeScreenActionListener {

    fun onSettingValueChanged(value: String)
    fun onSettingItemSelected(setting: ISettingItemVO)
    fun onSignOffConfirmed()
    fun onSignOffCancelled()
}