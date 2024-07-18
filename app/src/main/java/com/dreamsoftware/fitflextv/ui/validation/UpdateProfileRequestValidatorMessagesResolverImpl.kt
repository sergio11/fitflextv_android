package com.dreamsoftware.fitflextv.ui.validation

import android.content.Context
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.domain.validation.IUpdateProfileRequestValidatorMessagesResolver

class UpdateProfileRequestValidatorMessagesResolverImpl(private val context: Context) :
    IUpdateProfileRequestValidatorMessagesResolver {

    override fun getInvalidAliasMessage(): String = context.getString(R.string.invalid_alias_message)
}