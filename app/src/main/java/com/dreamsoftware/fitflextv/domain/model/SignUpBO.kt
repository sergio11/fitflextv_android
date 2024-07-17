package com.dreamsoftware.fitflextv.domain.model

data class SignUpBO(
    val username: String,
    val password: String,
    val confirmPassword: String,
    val email: String,
    val firstName: String,
    val lastName: String
) {
    companion object {
        const val FIELD_EMAIL = "email"
        const val FIELD_USERNAME = "username"
        const val FIELD_PASSWORD = "password"
        const val FIELD_CONFIRM_PASSWORD = "confirmPassword"
        const val FIELD_FIRST_NAME = "firstName"
        const val FIELD_LAST_NAME = "lastName"
    }
}