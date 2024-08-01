package com.dreamsoftware.fitflextv.ui.screens.training

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import com.dreamsoftware.fitflextv.domain.model.SortTypeEnum
import com.dreamsoftware.fitflextv.ui.core.components.CommonCard
import com.dreamsoftware.fitflextv.ui.core.components.CommonFocusRequester
import com.dreamsoftware.fitflextv.ui.core.components.CommonLoadingState
import com.dreamsoftware.fitflextv.ui.core.components.CommonNoContentState
import com.dreamsoftware.fitflextv.ui.core.components.CommonOutlineButton
import com.dreamsoftware.fitflextv.ui.core.components.CommonTabRow
import com.dreamsoftware.fitflextv.ui.screens.training.components.FilterSideMenu
import com.dreamsoftware.fitflextv.ui.screens.training.components.SideMenu
import com.dreamsoftware.fitflextv.ui.screens.training.components.OptionsSideMenu
import com.dreamsoftware.fitflextv.ui.utils.conditional

@Composable
internal fun TrainingScreenContent(
    state: TrainingUiState,
    actionListener: TrainingScreenActionListener
) {
    with(actionListener) {
        with(state) {
            SideMenu(onDismissSideMenu = ::onDismissFilterSideMenu, isSideMenuExpended = isFilterExpended) {
                FilterSideMenu(
                    onClearFilters = ::onFilterCleared,
                    filtrationFields = filterItems,
                    onFieldClicked = ::onFilterFieldSelected
                )
            }
            SideMenu(onDismissSideMenu = ::onDismissFieldFilterSideMenu, isSideMenuExpended = isFieldFilterSelected) {
                selectedTrainingFilter?.run {
                    OptionsSideMenu(
                        onDismissSideMenu = ::onDismissFieldFilterSideMenu,
                        selectedIndex = selectedOption,
                        titleRes = title,
                        items = options,
                        onSelectedItem = ::onSelectedTrainingFilterOption
                    )
                }
            }
            SideMenu(onDismissSideMenu = ::onDismissSortSideMenu, isSideMenuExpended = isSortExpended) {
                OptionsSideMenu(
                    onDismissSideMenu = ::onSortCleared,
                    titleRes = R.string.sort_by,
                    items = SortTypeEnum.entries.map { it.value },
                    selectedIndex = selectedSortItem,
                    onSelectedItem = ::onSelectedSortedItem
                )
            }
            TrainingProgramList(
                state = this,
                actionListener = actionListener
            )
        }
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
                        text = "Sort by: ${SortTypeEnum.entries[state.selectedSortItem].value}",
                        onClick = actionListener::onSortedClicked
                    )
                    Spacer(modifier = Modifier.width(58.dp))
                }
            }

            item {
                if (state.isLoading) {
                    CommonLoadingState(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp)
                    )
                } else if(state.trainingPrograms.isEmpty()) {
                    CommonNoContentState(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp),
                        messageRes = R.string.trainings_not_programs_available
                    )
                }
                AnimatedVisibility(
                    visible = !state.isLoading && state.trainingPrograms.isNotEmpty(),
                    enter = fadeIn(
                        animationSpec = tween(800)
                    ),
                    exit = fadeOut(
                        animationSpec = tween(800)
                    ),
                ) {
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