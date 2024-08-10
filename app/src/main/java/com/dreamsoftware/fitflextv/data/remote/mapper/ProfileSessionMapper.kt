package com.dreamsoftware.fitflextv.data.remote.mapper

import com.dreamsoftware.fitflextv.data.preferences.dto.ProfileSelectedPreferenceDTO
import com.dreamsoftware.fitflextv.domain.model.AvatarTypeEnum
import com.dreamsoftware.fitflextv.domain.model.ProfileBO
import com.dreamsoftware.fitflextv.utils.IMapper
import com.dreamsoftware.fitflextv.utils.enumNameOfOrDefault

internal class ProfileSessionMapper: IMapper<ProfileBO, ProfileSelectedPreferenceDTO> {

    override fun mapInToOut(input: ProfileBO): ProfileSelectedPreferenceDTO = with(input) {
        ProfileSelectedPreferenceDTO(
            uuid = uuid,
            alias = alias,
            isSecured = isSecured,
            type = avatarType.name
        )
    }

    override fun mapInListToOutList(input: Iterable<ProfileBO>): Iterable<ProfileSelectedPreferenceDTO> =
        input.map(::mapInToOut)

    override fun mapOutToIn(input: ProfileSelectedPreferenceDTO): ProfileBO = with(input) {
        ProfileBO(
            uuid = uuid,
            alias = alias,
            isSecured = isSecured,
            avatarType = enumNameOfOrDefault(type, AvatarTypeEnum.BOY)
        )
    }

    override fun mapOutListToInList(input: Iterable<ProfileSelectedPreferenceDTO>): Iterable<ProfileBO> =
        input.map(::mapOutToIn)
}