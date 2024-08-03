package com.dreamsoftware.fitflextv.ui.screens.profiles.secure

interface SecurePinScreenActionListener {

    fun onUnlockPinChanged(unlockPin: String)
    fun onVerifyPressed()
    fun onCancelPressed()
    fun onErrorAccepted()
}