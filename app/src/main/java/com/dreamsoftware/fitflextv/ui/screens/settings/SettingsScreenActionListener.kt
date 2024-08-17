package com.dreamsoftware.fitflextv.ui.screens.settings

import com.dreamsoftware.fudge.core.IFudgeTvScreenActionListener

interface SettingsScreenActionListener: IFudgeTvScreenActionListener {

    fun onSettingValueChanged(value: String)
    fun onSettingItemSelected(setting: ISettingItemVO)
    fun onSignOffConfirmed()
    fun onSignOffCancelled()
}