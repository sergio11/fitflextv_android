package com.dreamsoftware.fitflextv.ui.screens.more_options

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dreamsoftware.fitflextv.data.repository.training.TrainingRepository
import com.dreamsoftware.fitflextv.utils.Result
import com.dreamsoftware.fitflextv.utils.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MoreOptionsViewModel @Inject constructor(
    trainingRepository: TrainingRepository
) :
    ViewModel() {

    val uiState: StateFlow<MoreOptionsUiState> =
        trainingRepository.getTrainingById(1)
            .asResult()
            .map {
                when (it) {
                    is Result.Loading -> MoreOptionsUiState.Loading
                    is Result.Error -> MoreOptionsUiState.Error
                    is Result.Success -> {
                        MoreOptionsUiState.Ready(
                            trainingDetailsBO = it.data
                        )
                    }
                }
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = MoreOptionsUiState.Loading,
            )

}