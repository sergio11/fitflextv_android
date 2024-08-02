package com.dreamsoftware.fitflextv.ui.screens.profiles.advance

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRestorer
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Tab
import androidx.tv.material3.TabDefaults
import androidx.tv.material3.TabRow
import androidx.tv.material3.TabRowDefaults
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.ui.core.components.CommonText
import com.dreamsoftware.fitflextv.ui.core.components.CommonTextTypeEnum
import com.dreamsoftware.fitflextv.ui.screens.profiles.components.CommonProfileScreenContent

@Composable
fun ProfileAdvanceScreenContent(
    uiState: ProfileAdvanceUiState,
    onConfirmPressed: () -> Unit,
    onDeleteProfilePressed: () -> Unit,
    onNewTabSelected: (ProfileAdvancedTab) -> Unit,
    onErrorAccepted: () -> Unit
) {
    with(uiState) {
        CommonProfileScreenContent(
            isLoading = isLoading,
            error = errorMessage,
            onErrorAccepted = onErrorAccepted,
            mainTitleRes = R.string.profiles_advance_main_title,
            secondaryTitleRes = R.string.profiles_advance_main_description,
            primaryOptionTextRes = R.string.profiles_advance_form_confirm_button_text,
            secondaryOptionTextRes = R.string.profiles_advance_form_delete_button_text,
            onPrimaryOptionPressed = onConfirmPressed,
            onSecondaryOptionPressed = onDeleteProfilePressed
        ) {
            ProfileAdvanceTabs(
                tabs = tabs,
                tabSelected = tabSelected,
                onTabSelected = onNewTabSelected
            )
            when(tabSelected) {
                ProfileAdvancedTab.ChangeSecurePinTab -> ProfileAdvanceChangeSecurePin()
                ProfileAdvancedTab.TimeRestrictionsTab -> ProfileAdvanceTimeRestrictions()
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun ProfileAdvanceTabs(
    tabs: List<ProfileAdvancedTab>,
    tabSelected: ProfileAdvancedTab,
    onTabSelected: (ProfileAdvancedTab) -> Unit
) {
    with(MaterialTheme.colorScheme) {
        val selectedTabIndex = tabs.indexOfFirst { it == tabSelected }.takeIf { it >= 0 } ?: 0
        TabRow(
            selectedTabIndex = selectedTabIndex,
            indicator = { tabPositions, doesTabRowHaveFocus ->
                TabRowDefaults.PillIndicator(
                    currentTabPosition = tabPositions[selectedTabIndex],
                    doesTabRowHaveFocus = doesTabRowHaveFocus,
                    activeColor = primary,
                    inactiveColor = secondary
                )
            },
            modifier = Modifier.focusRestorer()
        ) {
            tabs.forEachIndexed { index, tab ->
                key(index) {
                    Tab(
                        selected = index == selectedTabIndex,
                        onFocus = { onTabSelected(tab) },
                        colors = TabDefaults.underlinedIndicatorTabColors(),
                    ) {
                        CommonText(
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                            type = CommonTextTypeEnum.LABEL_LARGE,
                            titleRes = tab.titleRes
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ProfileAdvanceChangeSecurePin() {

}

@Composable
private fun ProfileAdvanceTimeRestrictions() {

}