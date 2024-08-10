package com.dreamsoftware.fitflextv.data.repository.mapper

import com.dreamsoftware.fitflextv.data.preferences.dto.UserPreferencesDTO
import com.dreamsoftware.fitflextv.domain.model.AppLanguageEnum
import com.dreamsoftware.fitflextv.domain.model.UnitsEnum
import com.dreamsoftware.fitflextv.domain.model.UserPreferenceBO
import com.dreamsoftware.fitflextv.domain.model.VideoQualityEnum
import com.dreamsoftware.fitflextv.utils.IMapper
import com.dreamsoftware.fitflextv.utils.enumValueOfOrDefault

internal class UserPreferencesMapper : IMapper<UserPreferencesDTO, UserPreferenceBO> {

    override fun mapInToOut(input: UserPreferencesDTO): UserPreferenceBO = with(input) {
        UserPreferenceBO(
            units = enumValueOfOrDefault(units, UnitsEnum.METRIC),
            language = enumValueOfOrDefault(language, AppLanguageEnum.ENGLISH),
            videoQuality = enumValueOfOrDefault(videoQuality, VideoQualityEnum.FULL_HD)
        )
    }

    override fun mapInListToOutList(input: Iterable<UserPreferencesDTO>): Iterable<UserPreferenceBO> =
        input.map(::mapInToOut)

    override fun mapOutToIn(input: UserPreferenceBO): UserPreferencesDTO = with(input) {
        UserPreferencesDTO(
            units = units.value,
            language = language.value,
            videoQuality = videoQuality.value
        )
    }

    override fun mapOutListToInList(input: Iterable<UserPreferenceBO>): Iterable<UserPreferencesDTO> =
        input.map(::mapOutToIn)
}