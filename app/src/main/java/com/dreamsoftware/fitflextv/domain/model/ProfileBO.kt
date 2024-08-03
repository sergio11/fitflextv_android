package com.dreamsoftware.fitflextv.domain.model

data class ProfileBO(
    val uuid: String,
    val alias: String,
    val isSecured: Boolean,
    val avatarType: AvatarTypeEnum
)