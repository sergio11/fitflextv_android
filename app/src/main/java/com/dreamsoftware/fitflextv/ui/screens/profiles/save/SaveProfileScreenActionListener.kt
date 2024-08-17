package com.dreamsoftware.fitflextv.ui.screens.profiles.save

import com.dreamsoftware.fitflextv.domain.model.AvatarTypeEnum
import com.dreamsoftware.fudge.core.IFudgeTvScreenActionListener

interface SaveProfileScreenActionListener: IFudgeTvScreenActionListener {

    fun onAliasChanged(alias: String)
    fun onPinChanged(pin: String)
    fun onAvatarTypeChanged(avatarType: AvatarTypeEnum)
    fun onSaveProfilePressed()
    fun onAdvanceConfigurationPressed()
    fun onCancelPressed()
}