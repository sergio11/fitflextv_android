package com.dreamsoftware.fitflextv.data.remote.dto

data class AuthUserDTO(
    val uid: String,
    val displayName: String? = null,
    val email: String? = null,
    val photoUrl: String? = null
)
