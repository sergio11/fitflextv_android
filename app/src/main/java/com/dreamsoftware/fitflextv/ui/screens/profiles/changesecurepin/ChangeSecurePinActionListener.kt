package com.dreamsoftware.fitflextv.ui.screens.profiles.changesecurepin

import com.dreamsoftware.fudge.core.IFudgeTvScreenActionListener

interface ChangeSecurePinActionListener: IFudgeTvScreenActionListener {

    fun onConfirmPressed()
    fun onDeleteProfilePressed()
    fun onCurrentSecurePinChanged(pin: String)
    fun onNewSecurePinChanged(pin: String)
    fun onCloseSecurePinUpdatedDialog()
}