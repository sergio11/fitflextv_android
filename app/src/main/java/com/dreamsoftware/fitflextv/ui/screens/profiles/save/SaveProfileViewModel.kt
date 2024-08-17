package com.dreamsoftware.fitflextv.ui.screens.profiles.save

import com.dreamsoftware.fitflextv.di.SaveProfileScreenErrorMapper
import com.dreamsoftware.fitflextv.domain.model.AvatarTypeEnum
import com.dreamsoftware.fitflextv.domain.model.ProfileBO
import com.dreamsoftware.fitflextv.domain.usecase.CreateProfileUseCase
import com.dreamsoftware.fitflextv.domain.usecase.GetProfileByIdUseCase
import com.dreamsoftware.fitflextv.domain.usecase.UpdateProfileUseCase
import com.dreamsoftware.fitflextv.ui.core.IErrorMapper
import com.dreamsoftware.fitflextv.ui.utils.EMPTY
import com.dreamsoftware.fudge.core.FudgeViewModel
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SaveProfileViewModel @Inject constructor(
    private val getProfileByIdUseCase: GetProfileByIdUseCase,
    private val createProfileUseCase: CreateProfileUseCase,
    private val updateProfileUseCase: UpdateProfileUseCase,
    @SaveProfileScreenErrorMapper private val errorMapper: IErrorMapper
): FudgeViewModel<SaveProfileUiState, SaveProfileSideEffects>(), SaveProfileScreenActionListener {

    override fun onGetDefaultState(): SaveProfileUiState = SaveProfileUiState()

    private lateinit var profileId: String

    fun load(profileId: String) {
        this.profileId = profileId
        executeUseCaseWithParams(
            useCase = getProfileByIdUseCase,
            params = GetProfileByIdUseCase.Params(profileId),
            onSuccess = ::onLoadProfileCompleted
        )
    }

    override fun onAvatarTypeChanged(avatarType: AvatarTypeEnum) {
        updateState {
            it.copy(avatarType = avatarType)
        }
    }

    override fun onSaveProfilePressed() {
        with(uiState.value) {
            if(isEditMode) {
                onUpdateProfile()
            } else {
                onCreateProfile()
            }
        }
    }

    override fun onAdvanceConfigurationPressed() {
        launchSideEffect(SaveProfileSideEffects.OpenAdvanceConfiguration(profileId))
    }

    override fun onCancelPressed() {
        launchSideEffect(SaveProfileSideEffects.CancelConfiguration)
    }

    override fun onAliasChanged(alias: String) {
        updateState {
            it.copy(alias = alias)
        }
    }

    override fun onPinChanged(pin: String) {
        updateState {
            it.copy(securePin = pin)
        }
    }

    private fun onUpdateProfile() {
        with(uiState.value) {
            executeUseCaseWithParams(
                useCase = updateProfileUseCase,
                params = UpdateProfileUseCase.Params(
                    profileId = profileId,
                    alias = alias,
                    avatarType  = avatarType
                ),
                onSuccess = {
                    onSaveProfileSuccessfully()
                },
                onMapExceptionToState = ::onMapExceptionToState
            )
        }
    }

    private fun onCreateProfile() {
        with(uiState.value) {
            executeUseCaseWithParams(
                useCase = createProfileUseCase,
                params = CreateProfileUseCase.Params(
                    alias = alias,
                    pin = securePin.toIntOrNull(),
                    avatarType = avatarType
                ),
                onSuccess = {
                    onSaveProfileSuccessfully()
                },
                onMapExceptionToState = ::onMapExceptionToState
            )
        }
    }

    private fun onLoadProfileCompleted(profileBO: ProfileBO) {
        updateState {
            it.copy(
                isEditMode = true,
                alias = profileBO.alias,
                avatarType = profileBO.avatarType
            )
        }
    }

    private fun onSaveProfileSuccessfully() {
        launchSideEffect(SaveProfileSideEffects.SaveProfileSuccessfully)
    }

    private fun onMapExceptionToState(ex: Exception, uiState: SaveProfileUiState) =
        uiState.copy(
            isLoading = false,
            errorMessage = errorMapper.mapToMessage(ex)
        )
}

data class SaveProfileUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val isEditMode: Boolean = false,
    val alias: String = String.EMPTY,
    val aliasError: String = String.EMPTY,
    val securePin: String = String.EMPTY,
    val securePinError: String = String.EMPTY,
    val avatarType: AvatarTypeEnum = AvatarTypeEnum.BOY
): UiState<SaveProfileUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): SaveProfileUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface SaveProfileSideEffects: SideEffect {
    data object SaveProfileSuccessfully: SaveProfileSideEffects
    data class OpenAdvanceConfiguration(val profileId: String): SaveProfileSideEffects
    data object CancelConfiguration: SaveProfileSideEffects
}