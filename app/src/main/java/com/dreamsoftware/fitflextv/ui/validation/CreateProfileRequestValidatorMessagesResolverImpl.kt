package com.dreamsoftware.fitflextv.ui.validation

import android.content.Context
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.domain.validation.ICreateProfileRequestValidatorMessagesResolver

class CreateProfileRequestValidatorMessagesResolverImpl(private val context: Context) :
    ICreateProfileRequestValidatorMessagesResolver {
    override fun getInvalidPinMessage(): String = context.getString(R.string.invalid_secure_pin_message)

    override fun getInvalidAliasMessage(): String = context.getString(R.string.invalid_alias_message)
}