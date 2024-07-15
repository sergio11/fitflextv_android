package com.dreamsoftware.fitflextv.data.remote.dto.request

data class CreateUserDTO(
    val uid: String,
    val email: String,
    val username: String,
    val firstName: String,
    val lastName: String
)
