package com.dreamsoftware.fitflextv.data.repository.mapper

import com.dreamsoftware.fitflextv.data.remote.dto.response.ProfileDTO
import com.dreamsoftware.fitflextv.domain.model.AvatarTypeEnum
import com.dreamsoftware.fitflextv.domain.model.ProfileBO
import com.dreamsoftware.fitflextv.utils.IOneSideMapper
import com.dreamsoftware.fitflextv.utils.enumNameOfOrDefault

internal class ProfileMapper : IOneSideMapper<ProfileDTO, ProfileBO> {

    override fun mapInToOut(input: ProfileDTO): ProfileBO = with(input) {
        ProfileBO(
            uuid = uuid,
            alias = alias,
            isSecured = isSecured,
            avatarType = enumNameOfOrDefault(avatarType, AvatarTypeEnum.BOY)
        )
    }

    override fun mapInListToOutList(input: Iterable<ProfileDTO>): Iterable<ProfileBO> =
        input.map(::mapInToOut)
}