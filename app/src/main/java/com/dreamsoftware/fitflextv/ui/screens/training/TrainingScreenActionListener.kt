package com.dreamsoftware.fitflextv.ui.screens.training

interface TrainingScreenActionListener {

    fun onFilterClicked()
    fun onSortedClicked()
    fun onDismissSortSideMenu()
    fun onDismissFilterSideMenu()
    fun onDismissFieldFilterSideMenu()
    fun onFilterFieldSelected(field: TrainingFilterVO)
    fun onSelectedSortedItem(currentIndex: Int)
    fun onChangeSelectedTab(index: Int)
    fun onChangeFocusTab(index: Int)
    fun onItemClicked(id: String)
}