package com.dreamsoftware.fitflextv.ui.screens.training

import com.dreamsoftware.fudge.core.IFudgeScreenActionListener

interface TrainingScreenActionListener: IFudgeScreenActionListener {

    fun onFilterClicked()
    fun onSortedClicked()
    fun onSortCleared()
    fun onDismissSortSideMenu()
    fun onDismissFilterSideMenu()
    fun onFilterCleared()
    fun onDismissFieldFilterSideMenu()
    fun onFilterFieldSelected(trainingFilter: TrainingFilterVO)
    fun onSelectedSortedItem(currentIndex: Int)
    fun onSelectedTrainingFilterOption(currentIndex: Int)
    fun onChangeSelectedTab(index: Int)
    fun onChangeFocusTab(index: Int)
    fun onItemClicked(id: String)
}