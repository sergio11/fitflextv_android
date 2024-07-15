package com.dreamsoftware.fitflextv.data.remote.dto.request

/**
 * Data Transfer Object (DTO) representing the data required to sign in a user through a network request.
 *
 * @property email The email address of the user.
 * @property password The password associated with the user's account.
 */
data class SignInUserDTO(
    val email: String,
    val password: String
)
