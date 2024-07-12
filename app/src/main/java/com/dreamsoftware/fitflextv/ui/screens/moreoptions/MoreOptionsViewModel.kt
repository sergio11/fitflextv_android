package com.dreamsoftware.fitflextv.ui.screens.moreoptions

import com.dreamsoftware.fitflextv.domain.model.ITrainingProgramBO
import com.dreamsoftware.fitflextv.domain.model.TrainingTypeEnum
import com.dreamsoftware.fitflextv.domain.usecase.GetTrainingByIdUseCase
import com.dreamsoftware.fitflextv.ui.core.BaseViewModel
import com.dreamsoftware.fitflextv.ui.core.SideEffect
import com.dreamsoftware.fitflextv.ui.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoreOptionsViewModel @Inject constructor(
    private val getTrainingByIdUseCase: GetTrainingByIdUseCase
) : BaseViewModel<MoreOptionsUiState, MoreOptionsSideEffects>() {

    override fun onGetDefaultState(): MoreOptionsUiState = MoreOptionsUiState()

    fun fetchData(id: String, type: TrainingTypeEnum) {
        executeUseCaseWithParams(
            useCase = getTrainingByIdUseCase,
            params = GetTrainingByIdUseCase.Params(id, type),
            onSuccess = ::onGetTrainingProgramByIdSuccessfully
        )
    }

    private fun onGetTrainingProgramByIdSuccessfully(trainingProgram: ITrainingProgramBO) {
        updateState { it.copy(trainingProgram = trainingProgram) }
    }
}

data class MoreOptionsUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val trainingProgram: ITrainingProgramBO? = null
): UiState<MoreOptionsUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): MoreOptionsUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface MoreOptionsSideEffects: SideEffect