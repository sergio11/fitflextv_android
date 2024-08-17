package com.dreamsoftware.fitflextv.ui.screens.instructordetail

import com.dreamsoftware.fitflextv.domain.model.InstructorBO
import com.dreamsoftware.fitflextv.domain.usecase.GetInstructorDetailUseCase
import com.dreamsoftware.fudge.core.FudgeTvViewModel
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InstructorDetailScreenViewModel @Inject constructor(
    private val getInstructorDetailUseCase: GetInstructorDetailUseCase
) : FudgeTvViewModel<InstructorDetailUiState, InstructorDetailSideEffects>(), InstructorDetailActionListener {

    override fun onGetDefaultState(): InstructorDetailUiState = InstructorDetailUiState()

    fun fetchData(id: String) {
        executeUseCaseWithParams(
            useCase = getInstructorDetailUseCase,
            params = GetInstructorDetailUseCase.Params(id),
            onSuccess = ::onGetInstructorDetailsCompleted
        )
    }

    private fun onGetInstructorDetailsCompleted(instructorDetail: InstructorBO) {
        updateState { it.copy(instructorDetail = instructorDetail) }
    }

    override fun onBackPressed() {
        launchSideEffect(InstructorDetailSideEffects.ExitFromInstructorDetail)
    }
}

data class InstructorDetailUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val instructorDetail: InstructorBO? = null
): UiState<InstructorDetailUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): InstructorDetailUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface InstructorDetailSideEffects : SideEffect {
    data object ExitFromInstructorDetail: InstructorDetailSideEffects
}