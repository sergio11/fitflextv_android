package com.dreamsoftware.fitflextv.domain.model

data class CreateProfileRequestBO(
    val uid: String,
    val alias: String,
    val pin: Int?,
    val avatarType: AvatarTypeEnum,
    val userId: String
) {
    companion object {
        const val FIELD_ALIAS = "alias"
        const val FIELD_PIN = "pin"
        const val FIELD_USER_ID = "userId"
    }
}