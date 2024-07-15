package com.dreamsoftware.fitflextv.data.remote.dto.request


data class CreateProfileRequestDTO(
    val uid: String,
    val alias: String,
    val pin: Int?,
    val avatarType: String,
    val userId: String
)
