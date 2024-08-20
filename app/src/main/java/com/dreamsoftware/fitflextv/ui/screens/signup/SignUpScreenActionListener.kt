package com.dreamsoftware.fitflextv.ui.screens.signup

import com.dreamsoftware.fudge.core.IFudgeTvScreenActionListener

interface SignUpScreenActionListener: IFudgeTvScreenActionListener {

    fun onFirstNameChanged(newFirstName: String)
    fun onLastNameChanged(newLastName: String)
    fun onEmailChanged(newEmail: String)
    fun onUsernameChanged(newUsername: String)
    fun onPasswordChanged(newPassword: String)
    fun onRepeatPasswordChanged(newRepeatPassword: String)
    fun onSigUpPressed()
    fun onCancelPressed()
}