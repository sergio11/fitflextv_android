package com.dreamsoftware.fitflextv.ui.screens.profiles.save

import android.content.Context
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.domain.exception.InvalidDataException
import com.dreamsoftware.fitflextv.ui.core.IErrorMapper

class SaveProfileScreenSimpleErrorMapper(
    private val context: Context
): IErrorMapper {
    override fun mapToMessage(ex: Throwable): String = context.getString(when(ex) {
        is InvalidDataException -> R.string.save_profile_form_invalid_data_provided
        else -> R.string.generic_error_exception
    })
}