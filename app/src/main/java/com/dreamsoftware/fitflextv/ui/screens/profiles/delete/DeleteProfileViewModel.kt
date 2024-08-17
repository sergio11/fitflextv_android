package com.dreamsoftware.fitflextv.ui.screens.profiles.delete

import com.dreamsoftware.fitflextv.domain.model.ProfileBO
import com.dreamsoftware.fitflextv.domain.usecase.DeleteProfileUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetProfileByIdUseCase
import com.dreamsoftware.fudge.core.FudgeTvViewModel
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DeleteProfileViewModel @Inject constructor(
    private val getProfileByIdUseCase: GetProfileByIdUseCase,
    private val deleteProfileUseCase: DeleteProfileUseCase
): FudgeTvViewModel<DeleteProfileUiState, DeleteProfileSideEffects>(), DeleteProfileScreenActionListener {

    override fun onGetDefaultState(): DeleteProfileUiState = DeleteProfileUiState()

    fun load(profileId: String) {
        executeUseCaseWithParams(
            useCase = getProfileByIdUseCase,
            params = GetProfileByIdUseCase.Params(profileId),
            onSuccess = ::onLoadProfileCompleted
        )
    }

    private fun onLoadProfileCompleted(profileBO: ProfileBO) {
        updateState {
            it.copy(profile = profileBO)
        }
    }

    private fun onProfileDeleted() {
        launchSideEffect(DeleteProfileSideEffects.ProfileDeleteSuccessfully)
    }

    override fun onDeletePressed() {
        with(uiState.value) {
            profile?.let {
                executeUseCaseWithParams(
                    useCase = deleteProfileUseCase,
                    params = DeleteProfileUseCase.Params(profileId = it.uuid),
                    onSuccess = {
                        onProfileDeleted()
                    }
                )
            }
        }
    }

    override fun onCancelPressed() {
        launchSideEffect(DeleteProfileSideEffects.CancelConfiguration)
    }
}

data class DeleteProfileUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val profile: ProfileBO? = null
): UiState<DeleteProfileUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): DeleteProfileUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface DeleteProfileSideEffects: SideEffect {
    data object ProfileDeleteSuccessfully: DeleteProfileSideEffects
    data object CancelConfiguration: DeleteProfileSideEffects
}