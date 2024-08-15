package com.dreamsoftware.fitflextv.ui.screens.settings

interface SettingsScreenActionListener {

    fun onSettingValueChanged(value: String)
    fun onSettingItemSelected(setting: ISettingItemVO)
    fun onSignOffConfirmed()
    fun onSignOffCancelled()
    fun onErrorAccepted()
}