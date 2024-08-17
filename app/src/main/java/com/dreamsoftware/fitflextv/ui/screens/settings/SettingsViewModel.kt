package com.dreamsoftware.fitflextv.ui.screens.settings

import androidx.annotation.StringRes
import com.dreamsoftware.fitflextv.AppEvent
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.domain.model.AppLanguageEnum
import com.dreamsoftware.fitflextv.domain.model.UnitsEnum
import com.dreamsoftware.fitflextv.domain.model.UserPreferenceBO
import com.dreamsoftware.fitflextv.domain.model.VideoQualityEnum
import com.dreamsoftware.fitflextv.domain.usecase.GetUserPreferencesUseCase
import com.dreamsoftware.fitflextv.domain.usecase.SaveUserPreferencesUseCase
import com.dreamsoftware.fitflextv.domain.usecase.SignOffUseCase
import com.dreamsoftware.fudge.core.FudgeTvViewModel
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
import com.dreamsoftware.fudge.utils.FudgeTvEventBus
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val signOffUseCase: SignOffUseCase,
    private val appEventBus: FudgeTvEventBus,
    private val getUserPreferencesUseCase: GetUserPreferencesUseCase,
    private val saveUserPreferencesUseCase: SaveUserPreferencesUseCase
) : FudgeTvViewModel<SettingsUiState, SettingsSideEffects>(), SettingsScreenActionListener {

    fun fetchData() {
        executeUseCase(useCase = getUserPreferencesUseCase, onSuccess = ::onFetchUserPreferencesCompleted)
    }

    override fun onGetDefaultState(): SettingsUiState = SettingsUiState(
        settingList = onBuildSettingsList()
    )

    override fun onSettingValueChanged(value: String) {
        uiState.value.settingSelected?.let { itemSelected ->
            updateState {
                it.copy(
                    settingList = it.settingList
                        .map { item ->
                            when {
                                item == itemSelected && item is ISettingItemVO.ISettingValueItemVO.SettingMultipleValuesVO -> item.copy(value = value)
                                item == itemSelected && item is ISettingItemVO.ISettingValueItemVO.SettingSingleValueVO -> item.copy(value = value)
                                else -> item
                            }
                        },
                    settingSelected = null
                )
            }
            onSaveUserPreferences()
        }
    }

    override fun onSettingItemSelected(setting: ISettingItemVO) {
        if(setting is ISettingItemVO.ISettingValueItemVO) {
            updateState { it.copy(settingSelected = setting) }
        } else if(setting is ISettingItemVO.SettingActionVO) {
            when(setting.type) {
                SettingActionTypeEnum.SIGN_OFF ->  updateState { it.copy(showSignOffDialog = true) }
                SettingActionTypeEnum.SUBSCRIPTIONS -> onOpenSubscriptions()
            }
        }
    }

    override fun onSignOffConfirmed() {
        updateState { it.copy(showSignOffDialog = false) }
        onSignOff()
    }

    override fun onSignOffCancelled() {
        updateState { it.copy(showSignOffDialog = false) }
    }

    private fun onSignOff() {
        executeUseCase(
            useCase = signOffUseCase,
            onSuccess = {
                onSignOffCompleted()
            },
            onFailed = {
                onSignOffCompleted()
            }
        )
    }

    private fun onSignOffCompleted() {
        appEventBus.send(AppEvent.SignOff)
    }

    private fun onOpenSubscriptions() {
        launchSideEffect(SettingsSideEffects.OpenSubscriptions)
    }

    private fun onFetchUserPreferencesCompleted(userPreferences: UserPreferenceBO) {
        updateState { it.copy(settingList = onBuildSettingsList(userPreferences)) }
    }

    private fun onSaveUserPreferences() {
        uiState.value.settingList.filterIsInstance<ISettingItemVO.ISettingValueItemVO.SettingMultipleValuesVO>()
            .let { settings ->
                executeUseCaseWithParams(
                    useCase = saveUserPreferencesUseCase,
                    params = SaveUserPreferencesUseCase.Params(
                        units = settings.find { it.type == SettingTypeEnum.UNITS }?.value.orEmpty(),
                        language = settings.find { it.type == SettingTypeEnum.APP_LANGUAGE }?.value.orEmpty(),
                        videoQuality = settings.find { it.type == SettingTypeEnum.VIDEO_QUALITY }?.value.orEmpty()
                    )
                )
            }
    }

    private fun onBuildSettingsList(userPreferences: UserPreferenceBO? = null) = listOf(
        ISettingItemVO.SettingHeaderVO(titleRes = R.string.app_settings),
        ISettingItemVO.ISettingValueItemVO.SettingMultipleValuesVO(
            titleRes = R.string.settings_units_preference_title,
            value = userPreferences?.units?.value ?: UnitsEnum.METRIC.value,
            type = SettingTypeEnum.UNITS,
            possibleValues = UnitsEnum.entries.map { it.value }
        ),
        ISettingItemVO.ISettingValueItemVO.SettingMultipleValuesVO(
            titleRes = R.string.settings_language_title,
            value = userPreferences?.language?.value ?: AppLanguageEnum.ENGLISH.value,
            type = SettingTypeEnum.APP_LANGUAGE,
            possibleValues = AppLanguageEnum.entries.map { it.value }
        ),
        ISettingItemVO.ISettingValueItemVO.SettingMultipleValuesVO(
            titleRes = R.string.settings_video_resolution_title,
            value =  userPreferences?.videoQuality?.value ?: VideoQualityEnum.FULL_HD.value,
            type = SettingTypeEnum.VIDEO_QUALITY,
            possibleValues = VideoQualityEnum.entries.map { it.value }
        ),
        ISettingItemVO.SettingHeaderVO(titleRes = R.string.about_settings),
        ISettingItemVO.ISettingValueItemVO.SettingSingleValueVO(
            titleRes = R.string.settings_about_app_title,
            valueRes = R.string.settings_about_app_content
        ),
        ISettingItemVO.ISettingValueItemVO.SettingSingleValueVO(
            titleRes = R.string.settings_about_me_title,
            valueRes = R.string.settings_about_me_content
        ),
        ISettingItemVO.SettingActionVO(
            titleRes = R.string.settings_close_session_title,
            type = SettingActionTypeEnum.SIGN_OFF
        ),
        ISettingItemVO.SettingActionVO(
            titleRes = R.string.settings_subscriptions_title,
            type = SettingActionTypeEnum.SUBSCRIPTIONS
        )
    )
}

data class SettingsUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val showSignOffDialog: Boolean = false,
    val settingList: List<ISettingItemVO> = emptyList(),
    val settingSelected: ISettingItemVO.ISettingValueItemVO? = null,
) : UiState<SettingsUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): SettingsUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface ISettingItemVO {
    val titleRes: Int

    sealed interface ISettingValueItemVO: ISettingItemVO {

        data class SettingSingleValueVO(
            @StringRes override val titleRes: Int,
            val value: String? = null,
            @StringRes val valueRes: Int? = null
        ) : ISettingValueItemVO

        data class SettingMultipleValuesVO(
            @StringRes override val titleRes: Int,
            val type: SettingTypeEnum,
            val value: String,
            val possibleValues: List<String> = emptyList(),
        ) : ISettingValueItemVO
    }

    data class SettingActionVO(
        @StringRes override val titleRes: Int,
        val type: SettingActionTypeEnum,
    ) : ISettingItemVO

    data class SettingHeaderVO(
        @StringRes override val titleRes: Int,
    ) : ISettingItemVO
}

enum class SettingTypeEnum {
    UNITS, APP_LANGUAGE, VIDEO_QUALITY
}

enum class SettingActionTypeEnum {
    SIGN_OFF, SUBSCRIPTIONS
}

sealed interface SettingsSideEffects : SideEffect {
    data object OpenSubscriptions: SettingsSideEffects
}