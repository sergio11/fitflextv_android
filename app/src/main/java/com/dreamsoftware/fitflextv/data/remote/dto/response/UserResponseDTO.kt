package com.dreamsoftware.fitflextv.data.remote.dto.response


data class UserResponseDTO(
    // The unique identifier of the user.
    val uuid: String,
    // The username of the user.
    val username: String,
    // The email address of the user.
    val email: String,
    // The first name of the user.
    val firstName: String,
    // The last name of the user.
    val lastName: String,
    val profilesCount: Int
)
