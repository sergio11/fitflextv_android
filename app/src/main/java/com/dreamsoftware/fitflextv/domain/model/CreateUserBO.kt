package com.dreamsoftware.fitflextv.domain.model

data class CreateUserBO(
    val username: String,
    val password: String,
    val email: String,
    val firstName: String,
    val lastName: String
)