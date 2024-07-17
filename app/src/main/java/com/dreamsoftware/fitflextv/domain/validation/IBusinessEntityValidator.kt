package com.dreamsoftware.fitflextv.domain.validation

interface IBusinessEntityValidator<T> {
    fun validate(entity: T): Map<String, String>
}