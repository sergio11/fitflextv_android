package com.dreamsoftware.fitflextv.ui.screens.player.video

import android.net.Uri
import android.os.Build
import androidx.annotation.OptIn
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.PlayerView
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.dreamsoftware.fitflextv.R
import com.dreamsoftware.fitflextv.ui.core.components.CommonFillButton
import com.dreamsoftware.fitflextv.ui.screens.player.components.PlayerTitle
import com.dreamsoftware.fitflextv.ui.screens.player.video.components.VideoPlayerControlsIcon
import com.dreamsoftware.fitflextv.ui.screens.player.video.components.VideoPlayerFrame
import com.dreamsoftware.fitflextv.ui.screens.player.video.components.VideoPlayerOverlay
import com.dreamsoftware.fitflextv.ui.screens.player.video.components.VideoPlayerSeeker
import com.dreamsoftware.fitflextv.ui.screens.player.video.components.VideoPlayerState
import com.dreamsoftware.fitflextv.ui.screens.player.video.components.rememberVideoPlayerState
import com.dreamsoftware.fitflextv.ui.theme.FitFlexTVTheme
import com.dreamsoftware.fitflextv.ui.utils.dPadVideoEvents
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@OptIn(UnstableApi::class)
@Composable
internal fun VideoPlayerScreenContent(
    state: VideoPlayerUiState,
) {

    val context = LocalContext.current
    val videoPlayerState = rememberVideoPlayerState(hideSeconds = 4)

    val exoPlayer = remember {
        ExoPlayer.Builder(context)
            .setMediaSourceFactory(ProgressiveMediaSource.Factory(DefaultDataSource.Factory(context)))
            .setVideoScalingMode(C.VIDEO_SCALING_MODE_SCALE_TO_FIT)
            .build()
            .apply {
                playWhenReady = true
                repeatMode = Player.REPEAT_MODE_OFF
            }
    }

    LaunchedEffect(exoPlayer, state) {
        exoPlayer.setMediaItem(
            MediaItem.Builder()
                .setUri(state.videoUrl)
                .setSubtitleConfigurations(
                    if (state.subtitles == null) {
                        emptyList()
                    } else {
                        listOf(
                            MediaItem.SubtitleConfiguration
                                .Builder(Uri.parse(state.subtitleUri))
                                .setMimeType("application/vtt")
                                .setLanguage("en")
                                .setSelectionFlags(C.SELECTION_FLAG_AUTOSELECT)
                                .build()
                        )
                    }
                ).build()
        )
        exoPlayer.prepare()
    }

    var contentCurrentPosition by remember { mutableLongStateOf(0L) }
    var isPlaying: Boolean by remember { mutableStateOf(exoPlayer.isPlaying) }
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            contentCurrentPosition = exoPlayer.currentPosition
            isPlaying = exoPlayer.isPlaying
        }
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
        Box(
            Modifier
                .dPadVideoEvents(
                    exoPlayer,
                    videoPlayerState,
                )
                .focusable()
        ) {
            AndroidView(
                factory = {
                    PlayerView(context)
                        .apply { useController = false }
                },
                update = { it.player = exoPlayer },
                onRelease = { exoPlayer.release() }
            )

            val focusRequester = remember { FocusRequester() }

            VideoPlayerOverlay(
                modifier = Modifier.align(Alignment.BottomCenter),
                focusRequester = focusRequester,
                state = videoPlayerState,
                isPlaying = isPlaying,
                onBuildCenterButton = {
                    VideoPlayerControlsIcon(
                        modifier = Modifier.focusRequester(focusRequester),
                        icon = if (!isPlaying) R.drawable.play_icon else R.drawable.pause,
                        onClick = {
                            if (isPlaying) {
                                exoPlayer.play()
                            } else {
                                exoPlayer.pause()
                            }
                        },
                        state = videoPlayerState,
                        isPlaying = isPlaying,
                    )
                },
                onBuildSubtitles = {
                    AnimatedVisibility(visible = state.subtitles != null) {
                        Text(text = state.subtitles.toString())
                    }
                },
                onBuildControls = {
                    VideoPlayerControls(
                        isPlaying = isPlaying,
                        contentCurrentPosition = contentCurrentPosition,
                        exoPlayer = exoPlayer,
                        state = videoPlayerState,
                        title = state.title,
                        instructor = state.instructor,
                    )
                }
            )
        }
    }
}

@Composable
private fun VideoPlayerControls(
    isPlaying: Boolean,
    contentCurrentPosition: Long,
    exoPlayer: ExoPlayer,
    state: VideoPlayerState,
    title: String,
    instructor: String,
) {
    VideoPlayerFrame(
        videoTitle = {
            PlayerTitle(
                title = title,
                description = instructor,
                modifier = Modifier.fillMaxWidth()
            )
        },
        videoActions = {
            Row(
                modifier = Modifier.padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                VideoPlayerControlsIcon(
                    icon = R.drawable.subtitles,
                    state = state,
                    isPlaying = isPlaying,
                ) {}
                VideoPlayerControlsIcon(
                    icon = R.drawable.audio,
                    state = state,
                    isPlaying = isPlaying,
                ) {}
                VideoPlayerControlsIcon(
                    icon = R.drawable.settings,
                    state = state,
                    isPlaying = isPlaying,
                ) {}
                CommonFillButton(
                    text = stringResource(R.string.end_workout),
                    textStyle = MaterialTheme.typography.titleMedium
                        .copy(color = MaterialTheme.colorScheme.surface),
                    onClick = {}
                )
            }
        },
        videoSeeker = {
            VideoPlayerSeeker(
                state = state,
                onSeek = { exoPlayer.seekTo(exoPlayer.duration.times(it).toLong()) },
                contentProgress = contentCurrentPosition.milliseconds,
                contentDuration = exoPlayer.duration.milliseconds
            )
        }
    )
}


@Preview(device = Devices.TV_1080p)
@Composable
fun PreviewVideoPlayerScreen() {
    FitFlexTVTheme {
        VideoPlayerScreenContent(
            state = VideoPlayerUiState()
        )
    }
}