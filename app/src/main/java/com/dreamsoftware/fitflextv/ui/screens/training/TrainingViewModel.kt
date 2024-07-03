package com.dreamsoftware.fitflextv.ui.screens.training

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dreamsoftware.fitflextv.domain.repository.IInstructorRepository
import com.dreamsoftware.fitflextv.domain.repository.IWorkoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrainingViewModel @Inject constructor(
    private val iInstructorRepository: IInstructorRepository,
    private val IWorkoutRepository: IWorkoutRepository
) : ViewModel() {

    private val _state = MutableStateFlow(TrainingUiState())
    val state = _state.asStateFlow()

    init {
        getInstructors()
        getWorkout()
    }

    private fun getInstructors() {
        viewModelScope.launch {
            val result = iInstructorRepository.getInstructors()
            _state.update {
                it.copy(
                    instructorList = result,
                    filterSideMenuUiState = it.filterSideMenuUiState.copy(instructor = result.first())
                )
            }
        }
    }

    private fun getWorkout() {
        viewModelScope.launch {
            val result = IWorkoutRepository.getWorkouts()
            _state.update {
                it.copy(
                    workoutBOS = result
                )
            }
        }
    }

    fun onFilterClicked() {
        _state.update { it.copy(isFilterExpended = !it.isFilterExpended) }
    }

    fun onSortedClicked() {
        _state.update { it.copy(isSortExpended = !it.isSortExpended) }
    }

    fun onDismissSideMenu() {
        _state.update { it.copy(isFilterExpended = false, isSortExpended = false) }
    }

    fun onSelectedSortedItem(sortItemIndex: Int) {
        _state.update { it.copy(selectedSortItem = sortItemIndex) }
    }

    fun onChangeSelectedTab(index: Int) {
        _state.update { it.copy(selectedTab = index) }
    }

    fun onChangeFocusTab(index: Int) {
        _state.update { it.copy(focusTabIndex = index) }
    }
}