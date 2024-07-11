package com.dreamsoftware.fitflextv.ui.screens.training

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.grid.TvGridCells
import androidx.tv.foundation.lazy.grid.TvLazyHorizontalGrid
import androidx.tv.foundation.lazy.grid.itemsIndexed
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.ui.core.components.CommonCard
import com.dreamsoftware.fitflextv.ui.core.components.CommonFocusRequester
import com.dreamsoftware.fitflextv.ui.core.components.CommonLoadingState
import com.dreamsoftware.fitflextv.ui.core.components.CommonNoContentState
import com.dreamsoftware.fitflextv.ui.core.components.CommonOutlineButton
import com.dreamsoftware.fitflextv.ui.core.components.CommonTabRow
import com.dreamsoftware.fitflextv.ui.screens.training.components.FilterSideMenu
import com.dreamsoftware.fitflextv.ui.screens.training.components.SideMenu
import com.dreamsoftware.fitflextv.ui.screens.training.components.SortSideMenu
import com.dreamsoftware.fitflextv.ui.utils.conditional

@Composable
internal fun TrainingScreenContent(
    state: TrainingUiState,
    actionListener: TrainingScreenActionListener
) {
    with(actionListener) {
        SideMenu(onDismissSideMenu = ::onDismissSideMenu, isSideMenuExpended = state.isFilterExpended) {
            FilterSideMenu(
                onDismissSideMenu = ::onDismissSideMenu,
                filtrationFields = state.filterItems
            )
        }
        SideMenu(onDismissSideMenu = ::onDismissSideMenu, isSideMenuExpended = state.isSortExpended) {
            SortSideMenu(
                onDismissSideMenu = ::onDismissSideMenu,
                selectedIndex = state.selectedSortItem,
                onSelectedItem = ::onSelectedSortedItem
            )
        }
        TrainingProgramList(
            state = state,
            actionListener = actionListener
        )
    }
}

@Composable
private fun TrainingProgramList(
    state: TrainingUiState,
    actionListener: TrainingScreenActionListener
) {
    CommonFocusRequester { focusRequester ->
        TvLazyColumn(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Row(
                    modifier = Modifier.padding(top = 50.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Spacer(modifier = Modifier.width(20.dp))
                    CommonTabRow(
                        tabs = state.tabsTitle,
                        selectedTabIndex = state.selectedTab,
                        focusTabIndex = state.focusTabIndex,
                        onClick = actionListener::onChangeSelectedTab,
                        onFocus = actionListener::onChangeFocusTab,
                    )
                    Spacer(modifier = Modifier.weight(1F))
                    CommonOutlineButton(text = "Filters", onClick = actionListener::onFilterClicked)
                    Spacer(modifier = Modifier.width(14.dp))
                    CommonOutlineButton(
                        text = "Sort by: ${SortItem.entries[state.selectedSortItem]}",
                        onClick = actionListener::onSortedClicked
                    )
                    Spacer(modifier = Modifier.width(58.dp))
                }
            }

            item {
                if (state.isLoading) {
                    CommonLoadingState()
                } else if(state.trainingPrograms.isEmpty()) {
                    CommonNoContentState(messageRes = R.string.trainings_not_programs_available)
                } else {
                    TvLazyHorizontalGrid(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(650.dp),
                        rows = TvGridCells.Fixed(3),
                        horizontalArrangement = Arrangement.spacedBy(24.dp),
                        verticalArrangement = Arrangement.spacedBy(24.dp),
                        contentPadding = PaddingValues(32.dp)
                    ) {
                        itemsIndexed(state.trainingPrograms) { idx, training ->
                            with(MaterialTheme.typography) {
                                CommonCard(
                                    modifier = Modifier.conditional(condition = idx == 0, ifTrue = {
                                        focusRequester(focusRequester)
                                    }),
                                    imageUrl = training.imageUrl,
                                    title = training.name,
                                    timeText = training.duration,
                                    typeText = training.intensity.level,
                                    onClick = { actionListener.onItemClicked(training.id) },
                                    titleTextStyle = titleMedium,
                                    timeTextStyle = labelMedium,
                                    typeTextStyle = labelMedium,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}