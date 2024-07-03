package com.dreamsoftware.fitflextv.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dreamsoftware.fitflextv.domain.repository.ISessionRepository
import com.dreamsoftware.fitflextv.domain.repository.ITrainingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val ISessionRepository: ISessionRepository,
    private val ITrainingRepository: ITrainingRepository
) : ViewModel() {
    private val _state: MutableStateFlow<HomeUiState> by lazy { MutableStateFlow(HomeUiState()) }
    val state = _state.asStateFlow()

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    sessionBOS = ISessionRepository.getSessions(),
                    categories = ISessionRepository.getCategories(),
                    recommended = ITrainingRepository.getTrainingsRecommended()
                )
            }
        }
    }
}