package com.dreamsoftware.fitflextv.ui.screens.player.audio

import com.dreamsoftware.fitflextv.domain.model.TrainingSongBO
import com.dreamsoftware.fitflextv.domain.usecase.GetSongByIdUseCase
import com.dreamsoftware.fitflextv.ui.utils.EMPTY
import com.dreamsoftware.fudge.core.FudgeTvViewModel
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AudioPlayerViewModel @Inject constructor(
    private val getSongByIdUseCase: GetSongByIdUseCase
) : FudgeTvViewModel<AudioPlayerUiState, AudioPlayerSideEffects>() {

    override fun onGetDefaultState(): AudioPlayerUiState = AudioPlayerUiState()

    fun fetchData(songId: String) {
        executeUseCaseWithParams(
            useCase = getSongByIdUseCase,
            params = GetSongByIdUseCase.Params(songId),
            onSuccess = ::onGetSongByIdSuccessfully
        )
    }

    private fun onGetSongByIdSuccessfully(trainingSongBO: TrainingSongBO) {
        updateState {
            with(trainingSongBO) {
                it.copy(
                    title = title,
                    description = description,
                    author = author,
                    audioUrl = audioUrl,
                    id = id,
                    imageUrl = imageUrl,
                )
            }
        }
    }
}

data class AudioPlayerUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val id: String = String.EMPTY,
    val audioUrl: String = String.EMPTY,
    val title: String = String.EMPTY,
    val description: String = String.EMPTY,
    val author: String = String.EMPTY,
    val imageUrl: String = String.EMPTY,
): UiState<AudioPlayerUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): AudioPlayerUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface AudioPlayerSideEffects: SideEffect