package com.dreamsoftware.fitflextv.ui.screens.profiles.secure

import com.dreamsoftware.fudge.core.IFudgeScreenActionListener

interface SecurePinScreenActionListener: IFudgeScreenActionListener {

    fun onUnlockPinChanged(unlockPin: String)
    fun onVerifyPressed()
    fun onCancelPressed()
}