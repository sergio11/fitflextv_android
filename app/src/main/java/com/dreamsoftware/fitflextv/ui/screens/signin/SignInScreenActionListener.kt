package com.dreamsoftware.fitflextv.ui.screens.signin

import com.dreamsoftware.fudge.core.IFudgeTvScreenActionListener

interface SignInScreenActionListener: IFudgeTvScreenActionListener {

    fun onEmailChanged(newEmail: String)
    fun onPasswordChanged(newPassword: String)
    fun onSigInPressed()
    fun onGoToSignUp()
}