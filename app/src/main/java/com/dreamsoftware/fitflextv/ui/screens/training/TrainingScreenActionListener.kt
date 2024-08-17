package com.dreamsoftware.fitflextv.ui.screens.training

import com.dreamsoftware.fudge.component.menu.FudgeTvFilterVO
import com.dreamsoftware.fudge.core.IFudgeTvScreenActionListener

interface TrainingScreenActionListener: IFudgeTvScreenActionListener {

    fun onFilterClicked()
    fun onSortedClicked()
    fun onSortCleared()
    fun onDismissSortSideMenu()
    fun onDismissFilterSideMenu()
    fun onFilterCleared()
    fun onDismissFieldFilterSideMenu()
    fun onFilterFieldSelected(trainingFilter: FudgeTvFilterVO)
    fun onSelectedSortedItem(currentIndex: Int)
    fun onSelectedTrainingFilterOption(currentIndex: Int)
    fun onChangeSelectedTab(index: Int)
    fun onChangeFocusTab(index: Int)
    fun onItemClicked(id: String)
}