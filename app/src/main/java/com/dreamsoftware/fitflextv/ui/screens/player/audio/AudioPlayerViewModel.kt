package com.dreamsoftware.fitflextv.ui.screens.player.audio

import com.dreamsoftware.fitflextv.domain.model.SongBO
import com.dreamsoftware.fitflextv.domain.usecase.GetSongByIdUseCase
import com.dreamsoftware.fitflextv.ui.core.BaseViewModel
import com.dreamsoftware.fitflextv.ui.core.SideEffect
import com.dreamsoftware.fitflextv.ui.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AudioPlayerViewModel @Inject constructor(
    private val getSongByIdUseCase: GetSongByIdUseCase
) : BaseViewModel<AudioPlayerUiState, AudioPlayerSideEffects>() {

    override fun onGetDefaultState(): AudioPlayerUiState = AudioPlayerUiState()

    fun fetchData(songId: String) {
        executeUseCaseWithParams(
            useCase = getSongByIdUseCase,
            params = GetSongByIdUseCase.Params(songId),
            onSuccess = ::onGetSongByIdSuccessfully
        )
    }

    private fun onGetSongByIdSuccessfully(songBO: SongBO) {
        updateState {
            with(songBO) {
                it.copy(
                    title = title,
                    author = author,
                    audioUrl = audioUrl,
                    id = id,
                    imageUrl = imageUrl,
                    date = createdDate,
                )
            }
        }
    }
}

data class AudioPlayerUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val id: String = "",
    val audioUrl: String = "",
    val title: String = "",
    val author: String = "",
    val imageUrl: String? = null,
    val date: String? = null,
): UiState<AudioPlayerUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): AudioPlayerUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface AudioPlayerSideEffects: SideEffect