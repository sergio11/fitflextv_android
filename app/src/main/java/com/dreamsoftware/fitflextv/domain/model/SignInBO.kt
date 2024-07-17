package com.dreamsoftware.fitflextv.domain.model

data class SignInBO(
    val email: String,
    val password: String,
) {
    companion object {
        const val FIELD_EMAIL = "email"
        const val FIELD_PASSWORD = "password"
    }
}