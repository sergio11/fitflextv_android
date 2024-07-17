package com.dreamsoftware.fitflextv.domain.validation.impl

import com.dreamsoftware.fitflextv.domain.extensions.isProfileAliasNotValid
import com.dreamsoftware.fitflextv.domain.extensions.isSecurePinNotValid
import com.dreamsoftware.fitflextv.domain.model.CreateProfileRequestBO
import com.dreamsoftware.fitflextv.domain.validation.IBusinessEntityValidator
import com.dreamsoftware.fitflextv.domain.validation.ICreateProfileRequestValidatorMessagesResolver

internal class CreateProfileRequestValidatorImpl(
    private val messagesResolver: ICreateProfileRequestValidatorMessagesResolver
): IBusinessEntityValidator<CreateProfileRequestBO> {

    override fun validate(entity: CreateProfileRequestBO): Map<String, String> = buildMap {
        with(entity) {
            if( pin != null && pin.isSecurePinNotValid()) {
                put(CreateProfileRequestBO.FIELD_PIN, messagesResolver.getInvalidPinMessage())
            }
            if(alias.isProfileAliasNotValid()) {
                put(CreateProfileRequestBO.FIELD_ALIAS, messagesResolver.getInvalidAliasMessage())
            }
        }
    }
}