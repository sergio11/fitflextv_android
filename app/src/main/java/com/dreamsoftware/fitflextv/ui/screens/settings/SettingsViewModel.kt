package com.dreamsoftware.fitflextv.ui.screens.settings

import androidx.annotation.StringRes
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.domain.model.AppLanguageEnum
import com.dreamsoftware.fitflextv.domain.model.UnitsEnum
import com.dreamsoftware.fitflextv.domain.model.VideoQualityEnum
import com.dreamsoftware.fitflextv.domain.usecase.SignOffUseCase
import com.dreamsoftware.fitflextv.ui.core.BaseViewModel
import com.dreamsoftware.fitflextv.ui.core.SideEffect
import com.dreamsoftware.fitflextv.ui.core.UiState
import com.dreamsoftware.fitflextv.utils.AppEvent
import com.dreamsoftware.fitflextv.utils.AppEventBus
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val signOffUseCase: SignOffUseCase,
    private val appEventBus: AppEventBus
) : BaseViewModel<SettingsUiState, SettingsSideEffects>(), SettingsScreenActionListener {

    override fun onGetDefaultState(): SettingsUiState = SettingsUiState(
        settingList = listOf(
            ISettingItemVO.SettingHeaderVO(titleRes = R.string.app_settings),
            ISettingItemVO.ISettingValueItemVO.SettingMultipleValuesVO(
                titleRes = R.string.settings_units_preference_title,
                value = "Imperial",
                possibleValues = UnitsEnum.entries.map { it.value }
            ),
            ISettingItemVO.ISettingValueItemVO.SettingMultipleValuesVO(
                titleRes = R.string.settings_language_title,
                value = "English (US)",
                possibleValues = AppLanguageEnum.entries.map { it.value }
            ),
            ISettingItemVO.ISettingValueItemVO.SettingMultipleValuesVO(
                titleRes = R.string.settings_video_resolution_title,
                value = "Automatic up to 4k",
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
            )
        )
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
        }
    }

    override fun onSettingItemSelected(setting: ISettingItemVO) {
        if(setting is ISettingItemVO.ISettingValueItemVO) {
            updateState { it.copy(settingSelected = setting) }
        } else if(setting is ISettingItemVO.SettingActionVO) {
            when(setting.type) {
                SettingActionTypeEnum.SIGN_OFF -> onSignOff()
            }
        }
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
}

data class SettingsUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
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
            val value: String,
            val possibleValues: List<String> = emptyList(),
        ) : ISettingValueItemVO
    }

    data class SettingActionVO(
        @StringRes override val titleRes: Int,
        val type: SettingActionTypeEnum
    ) : ISettingItemVO

    data class SettingHeaderVO(
        @StringRes override val titleRes: Int
    ) : ISettingItemVO
}

enum class SettingActionTypeEnum {
    SIGN_OFF
}

sealed interface SettingsSideEffects : SideEffect