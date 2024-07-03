package com.dreamsoftware.fitflextv.ui.core

interface IErrorMapper {
    fun mapToMessage(ex: Throwable): String
}