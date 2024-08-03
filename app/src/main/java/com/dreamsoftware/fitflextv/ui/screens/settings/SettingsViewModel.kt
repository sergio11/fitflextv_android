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
            ISettingItemVO.SettingValueVO(
                titleRes = R.string.settings_units_preference_title,
                value = "Imperial",
                possibleValues = listOf("Imperial", "Metric")
            ),
            ISettingItemVO.SettingValueVO(
                titleRes = R.string.settings_language_title,
                value = "English (US)",
                possibleValues = listOf("English (US)", "Spanish", "French")
            ),
            ISettingItemVO.SettingValueVO(
                titleRes = R.string.settings_video_resolution_title,
                value = "Automatic up to 4k",
                possibleValues = listOf("Automatic up to 4k", "1080p", "720p")
            ),
            ISettingItemVO.SettingValueVO(
                titleRes = R.string.settings_about_app_title,
                value = String.EMPTY
            )
        )
    )

    override fun onSettingValueChanged(value: String) {
        uiState.value.settingSelected?.let { itemSelected ->
            updateState {
                it.copy(
                    settingList = it.settingList.filterIsInstance<ISettingItemVO.SettingValueVO>()
                        .map { item ->
                            if (item == itemSelected) {
                                item.copy(value = value)
                            } else {
                                item
                            }
                        },
                    settingSelected = null
                )
            }
        }
    }

    override fun onSettingItemSelected(setting: ISettingItemVO.SettingValueVO) {
        updateState { it.copy(settingSelected = setting) }
    }
}

data class SettingsUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val settingList: List<ISettingItemVO> = emptyList(),
    val settingSelected: ISettingItemVO.SettingValueVO? = null,
) : UiState<SettingsUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): SettingsUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface ISettingItemVO {
    val titleRes: Int

    data class SettingValueVO(
        @StringRes override val titleRes: Int,
        val value: String,
        val possibleValues: List<String> = emptyList(),
    ) : ISettingItemVO

    data class SettingHeaderVO(
        @StringRes override val titleRes: Int
    ) : ISettingItemVO
}

sealed interface SettingsSideEffects : SideEffect