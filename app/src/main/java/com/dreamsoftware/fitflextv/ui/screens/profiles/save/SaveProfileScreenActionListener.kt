package com.dreamsoftware.fitflextv.ui.screens.profiles.save

import com.dreamsoftware.fitflextv.domain.model.AvatarTypeEnum

interface SaveProfileScreenActionListener {

    fun onAliasChanged(alias: String)
    fun onPinChanged(pin: String)
    fun onAvatarTypeChanged(avatarType: AvatarTypeEnum)
    fun onSaveProfilePressed()
    fun onAdvanceConfigurationPressed()
    fun onCancelPressed()
    fun onErrorAccepted()
}