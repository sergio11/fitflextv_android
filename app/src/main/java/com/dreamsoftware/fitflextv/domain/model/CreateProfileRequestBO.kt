package com.dreamsoftware.fitflextv.domain.model

data class CreateProfileRequestBO(
    val uid: String,
    val alias: String,
    val pin: Int?,
    val avatarType: AvatarTypeEnum,
    val userId: String
)