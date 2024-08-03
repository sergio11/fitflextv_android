package com.dreamsoftware.fitflextv.data.preferences.dto

data class ProfileSelectedPreferenceDTO(
    val uuid: String,
    val alias: String,
    val isSecured: Boolean,
    val type: String
)