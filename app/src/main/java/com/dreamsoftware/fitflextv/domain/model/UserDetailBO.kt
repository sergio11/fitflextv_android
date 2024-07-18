package com.dreamsoftware.fitflextv.domain.model

data class UserDetailBO(
    val uuid: String,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val profilesCount: Int
)