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
import androidx.tv.foundation.lazy.grid.items
import androidx.tv.foundation.lazy.grid.itemsIndexed
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.fitflextv.ui.core.components.CommonCard
import com.dreamsoftware.fitflextv.ui.core.components.CommonFocusRequester
import com.dreamsoftware.fitflextv.ui.core.components.CommonOutlineButton
import com.dreamsoftware.fitflextv.ui.core.components.CommonTabRow
import com.dreamsoftware.fitflextv.ui.screens.training.components.FilterSideMenu
import com.dreamsoftware.fitflextv.ui.screens.training.components.SideMenu
import com.dreamsoftware.fitflextv.ui.screens.training.components.SortSideMenu
import com.dreamsoftware.fitflextv.ui.utils.conditional

@Composable
internal fun TrainingScreenContent(
    state: TrainingUiState,
    onClickFilter: () -> Unit,
    onClickSortBy: () -> Unit,
    onDismissSideMenu: () -> Unit,
    onSelectedItem: (currentIndex: Int) -> Unit,
    onChangeSelectedTab: (index: Int) -> Unit,
    onChangeFocusTab: (index: Int) -> Unit,
    onClickItem : (id:String) -> Unit
) {
    SideMenu(onDismissSideMenu = onDismissSideMenu, isSideMenuExpended = state.isFilterExpended) {
        FilterSideMenu(
            onDismissSideMenu = onDismissSideMenu,
            filtrationFields = state.filterItems
        )
    }
    SideMenu(onDismissSideMenu = onDismissSideMenu, isSideMenuExpended = state.isSortExpended) {
        SortSideMenu(
            onDismissSideMenu = onDismissSideMenu,
            selectedIndex = state.selectedSortItem,
            onSelectedItem = onSelectedItem
        )
    }
    val tabs = listOf("Workout", "Series", "Challenges", "Routines")
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
                        tabs = tabs,
                        selectedTabIndex = state.selectedTab,
                        focusTabIndex = state.focusTabIndex,
                        onClick = onChangeSelectedTab,
                        onFocus = onChangeFocusTab,
                    )
                    Spacer(modifier = Modifier.weight(1F))
                    CommonOutlineButton(text = "Filters", onClick = onClickFilter)
                    Spacer(modifier = Modifier.width(14.dp))
                    CommonOutlineButton(
                        text = "Sort by: ${SortItem.entries[state.selectedSortItem]}",
                        onClick = onClickSortBy
                    )
                    Spacer(modifier = Modifier.width(58.dp))
                }
            }

            item {
                TvLazyHorizontalGrid(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height((544.75).dp),
                    rows = TvGridCells.Fixed(3),
                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    contentPadding = PaddingValues(32.dp)
                ) {
                    itemsIndexed(state.workoutList) { idx, training ->
                        CommonCard(
                            modifier = Modifier.conditional(condition = idx == 0, ifTrue = {
                                focusRequester(focusRequester)
                            }),
                            imageUrl = training.imageUrl,
                            title = training.name,
                            timeText = training.duration,
                            typeText = training.intensityEnum.level,
                            onClick = { onClickItem(training.id) },
                            titleTextStyle = MaterialTheme.typography.titleMedium,
                            timeTextStyle = MaterialTheme.typography.labelMedium,
                            typeTextStyle = MaterialTheme.typography.labelMedium,
                        )
                    }
                }
            }
        }
    }
}