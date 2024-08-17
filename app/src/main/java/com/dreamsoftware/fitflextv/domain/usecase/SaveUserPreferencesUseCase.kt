package com.dreamsoftware.fitflextv.domain.usecase

import com.dreamsoftware.fitflextv.domain.model.AppLanguageEnum
import com.dreamsoftware.fitflextv.domain.model.UnitsEnum
import com.dreamsoftware.fitflextv.domain.model.UserPreferenceBO
import com.dreamsoftware.fitflextv.domain.model.VideoQualityEnum
import com.dreamsoftware.fitflextv.domain.repository.IUserRepository
import com.dreamsoftware.fitflextv.utils.enumOfOrDefault
import com.dreamsoftware.fudge.core.FudgeTvUseCaseWithParams

class SaveUserPreferencesUseCase(
    private val userRepository: IUserRepository
) : FudgeTvUseCaseWithParams<SaveUserPreferencesUseCase.Params, Unit>() {

    override suspend fun onExecuted(params: Params): Unit = with(params) {
        userRepository.saveUserPreferences(toUserPreferencesBO())
    }

    private fun Params.toUserPreferencesBO() = UserPreferenceBO(
        units = enumOfOrDefault({it.value == units}, UnitsEnum.METRIC),
        language = enumOfOrDefault({it.value == language}, AppLanguageEnum.ENGLISH),
        videoQuality = enumOfOrDefault({it.value == videoQuality}, VideoQualityEnum.FULL_HD)
    )

    data class Params(
        val units: String,
        val language: String,
        val videoQuality: String
    )
}