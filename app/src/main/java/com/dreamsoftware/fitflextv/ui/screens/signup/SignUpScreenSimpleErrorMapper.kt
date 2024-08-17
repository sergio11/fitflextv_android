package com.dreamsoftware.fitflextv.ui.screens.signup

import android.content.Context
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.domain.exception.InvalidDataException
import com.dreamsoftware.fudge.core.IFudgeTvErrorMapper

class SignUpScreenSimpleErrorMapper(
    private val context: Context
): IFudgeTvErrorMapper {
    override fun mapToMessage(ex: Throwable): String = context.getString(when(ex) {
        is InvalidDataException -> R.string.generic_form_invalid_data_provided
        else -> R.string.generic_error_exception
    })
}