package com.dreamsoftware.fitflextv.data.remote.dto.request

/**
 * Data Transfer Object (DTO) representing the structure of user data for sign-up in the network layer.
 *
 * @property username The username of the user.
 * @property password The password associated with the user.
 * @property email The email address of the user.
 * @property firstName The first name of the user.
 * @property lastName The last name of the user.
 */
data class SignUpUserDTO(
    val username: String,
    val password: String,
    val email: String,
    val firstName: String,
    val lastName: String
)
