package com.dreamsoftware.fitflextv.ui.screens.settings

import androidx.annotation.StringRes
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.ui.core.BaseViewModel
import com.dreamsoftware.fitflextv.ui.core.SideEffect
import com.dreamsoftware.fitflextv.ui.core.UiState
import com.dreamsoftware.fitflextv.ui.utils.EMPTY
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor() :
    BaseViewModel<SettingsUiState, SettingsSideEffects>(), SettingsScreenActionListener {

    override fun onGetDefaultState(): SettingsUiState = SettingsUiState(
        settingList = listOf(
            ISettingItemVO.SettingHeaderVO(titleRes = R.string.app_settings),
            ISettingItemVO.ISettingValueItemVO.SettingMultipleValuesVO(
                titleRes = R.string.settings_units_preference_title,
                value = "Imperial",
                possibleValues = listOf("Imperial", "Metric")
            ),
            ISettingItemVO.ISettingValueItemVO.SettingMultipleValuesVO(
                titleRes = R.string.settings_language_title,
                value = "English (US)",
                possibleValues = listOf("English (US)", "Spanish", "French")
            ),
            ISettingItemVO.ISettingValueItemVO.SettingMultipleValuesVO(
                titleRes = R.string.settings_video_resolution_title,
                value = "Automatic up to 4k",
                possibleValues = listOf("Automatic up to 4k", "1080p", "720p")
            ),
            ISettingItemVO.ISettingValueItemVO.SettingSingleValueVO(
                titleRes = R.string.settings_about_app_title,
                valueRes = R.string.settings_about_app_content
            ),
            ISettingItemVO.ISettingValueItemVO.SettingSingleValueVO(
                titleRes = R.string.settings_about_me_title,
                valueRes = R.string.settings_about_me_content
            ),
            ISettingItemVO.SettingActionVO(
                titleRes = R.string.settings_close_session_title
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
        }
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
        @StringRes override val titleRes: Int
    ) : ISettingItemVO

    data class SettingHeaderVO(
        @StringRes override val titleRes: Int
    ) : ISettingItemVO
}

sealed interface SettingsSideEffects : SideEffect