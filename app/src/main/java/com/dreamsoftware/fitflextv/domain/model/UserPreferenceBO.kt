package com.dreamsoftware.fitflextv.domain.model

data class UserPreferenceBO(
    val units: UnitsEnum,
    val language: AppLanguageEnum,
    val videoQuality: VideoQualityEnum
)
