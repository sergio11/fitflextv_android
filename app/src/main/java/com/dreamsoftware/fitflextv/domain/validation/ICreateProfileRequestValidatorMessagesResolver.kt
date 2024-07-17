package com.dreamsoftware.fitflextv.domain.validation

interface ICreateProfileRequestValidatorMessagesResolver {
    fun getInvalidPinMessage(): String
    fun getInvalidAliasMessage(): String
}