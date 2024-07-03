package com.dreamsoftware.fitflextv.ui.screens.subscription


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dreamsoftware.fitflextv.domain.model.SubscriptionBO
import com.dreamsoftware.fitflextv.domain.repository.IInstructorRepository
import com.dreamsoftware.fitflextv.utils.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.dreamsoftware.fitflextv.utils.Result

@HiltViewModel
class SubscriptionViewModel @Inject constructor(
    private val iInstructorRepository: IInstructorRepository
) : ViewModel() {

    private val _instructorImageState = MutableStateFlow("")
    val instructorImageState: StateFlow<String> = _instructorImageState.asStateFlow()

    private val _selectedSubscriptionBOOption = MutableStateFlow(
        SubscriptionBO(
            "",
            ""
        )
    )
    val selectedSubscriptionBOOption: StateFlow<SubscriptionBO> =
        _selectedSubscriptionBOOption.asStateFlow()

    init {
        viewModelScope.launch {
            _instructorImageState.value = iInstructorRepository.getInstructorImageById(INSTRUCTOR_ID)
        }
    }


    val uiState: StateFlow<SubscriptionUiState> =
        iInstructorRepository.getSubscriptionOptionsByInstructorId(INSTRUCTOR_ID)
            .asResult()
            .map {
                when (it) {
                    is Result.Loading -> SubscriptionUiState.Loading
                    is Result.Error -> SubscriptionUiState.Error
                    is Result.Success -> {
                        SubscriptionUiState.Ready(
                            subscriptionBOOptions = it.data
                        ).also { state ->
                            _selectedSubscriptionBOOption.value = state.subscriptionBOOptions.first()
                        }
                    }
                }
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = SubscriptionUiState.Loading,
            )


    fun updateSelectedSubscriptionOption(subscriptionBOOption: SubscriptionBO) {
        _selectedSubscriptionBOOption.value = subscriptionBOOption
    }

    companion object {
        private const val INSTRUCTOR_ID = "1"
    }
}