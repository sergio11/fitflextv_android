package com.dreamsoftware.fitflextv.domain.validation.impl

import android.util.Patterns
import com.dreamsoftware.fitflextv.domain.model.SignInBO
import com.dreamsoftware.fitflextv.domain.validation.IBusinessEntityValidator
import com.dreamsoftware.fitflextv.domain.validation.ISignInValidationMessagesResolver

internal class SignInValidatorImpl(
    private val messagesResolver: ISignInValidationMessagesResolver
): IBusinessEntityValidator<SignInBO> {

    private companion object {
        const val MIN_PASSWORD_LENGTH = 6
    }

    override fun validate(entity: SignInBO): Map<String, String> = buildMap {
        with(entity) {
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                put(SignInBO.FIELD_EMAIL, messagesResolver.getInvalidEmailMessage())
            }
            if (password.length < MIN_PASSWORD_LENGTH) {
                put(SignInBO.FIELD_PASSWORD, messagesResolver.getShortPasswordMessage(MIN_PASSWORD_LENGTH))
            }
        }
    }
}