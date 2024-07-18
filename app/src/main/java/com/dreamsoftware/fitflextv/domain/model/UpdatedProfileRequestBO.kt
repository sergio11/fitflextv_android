package com.dreamsoftware.fitflextv.domain.model

data class UpdatedProfileRequestBO(
    val alias: String? = null,
    val pin: Int? = null,
    val avatarType: AvatarTypeEnum? = null
) {
    companion object {
        const val FIELD_ALIAS = "alias"
    }
}