package com.dreamsoftware.fitflextv.ui.screens.profiles.selector

import com.dreamsoftware.fitflextv.domain.model.ProfileBO
import com.dreamsoftware.fudge.core.IFudgeScreenActionListener

interface ProfileSelectorScreenActionListener: IFudgeScreenActionListener {

    fun onProfileSelected(profile: ProfileBO)
    fun onAddProfilePressed()
    fun onProfileManagementPressed()
}