package com.dreamsoftware.fitflextv.ui.screens.profiles.changesecurepin

interface ChangeSecurePinActionListener {

    fun onConfirmPressed()
    fun onDeleteProfilePressed()
    fun onErrorAccepted()
    fun onCurrentSecurePinChanged(pin: String)
    fun onNewSecurePinChanged(pin: String)
}