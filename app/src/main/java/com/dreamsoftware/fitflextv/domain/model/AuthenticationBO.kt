package com.dreamsoftware.fitflextv.domain.model

data class AuthenticationBO(
    val uuid: String,
    val username: String,
    val profilesCount: Int
)