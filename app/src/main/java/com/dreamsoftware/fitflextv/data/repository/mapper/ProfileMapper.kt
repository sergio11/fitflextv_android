package com.dreamsoftware.fitflextv.data.repository.mapper

import android.util.Log
import com.dreamsoftware.fitflextv.data.remote.dto.response.ProfileDTO
import com.dreamsoftware.fitflextv.domain.model.AvatarTypeEnum
import com.dreamsoftware.fitflextv.domain.model.ProfileBO
import com.dreamsoftware.fitflextv.ui.utils.IOneSideMapper
import com.dreamsoftware.fitflextv.utils.enumValueOfOrDefault

internal class ProfileMapper : IOneSideMapper<ProfileDTO, ProfileBO> {

    override fun mapInToOut(input: ProfileDTO): ProfileBO = with(input) {
        Log.d("ATV_PROFILE_MAPPER", "ProfileMapper avatarType: $avatarType -> ${enumValueOfOrDefault(avatarType, AvatarTypeEnum.BOY)}")
        ProfileBO(
            uuid = uuid,
            alias = alias,
            isAdmin = isAdmin,
            isSecured = isSecured,
            avatarType = enumValueOfOrDefault(avatarType, AvatarTypeEnum.BOY)
        )
    }

    override fun mapInListToOutList(input: Iterable<ProfileDTO>): Iterable<ProfileBO> =
        input.map(::mapInToOut)
}