package com.dreamsoftware.fitflextv.domain.model

data class UpdatedProfileRequestBO(
    val alias: String? = null,
    val pin: Int? = null,
    val avatarType: String? = null
)