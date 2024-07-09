package com.dreamsoftware.fitflextv.ui.screens.training

interface TrainingScreenActionListener {

    fun onFilterClicked()
    fun onSortedClicked()
    fun onDismissSideMenu()
    fun onSelectedSortedItem(currentIndex: Int)
    fun onChangeSelectedTab(index: Int)
    fun onChangeFocusTab(index: Int)
    fun onItemClicked(id: String)
}