package com.dreamsoftware.fitflextv.ui.screens.profiles.selector

import com.dreamsoftware.fitflextv.domain.model.ProfileBO

interface ProfileSelectorScreenActionListener {

    fun onProfileSelected(profile: ProfileBO)
    fun onAddProfilePressed()
    fun onProfileManagementPressed()
    fun onErrorAccepted()
}