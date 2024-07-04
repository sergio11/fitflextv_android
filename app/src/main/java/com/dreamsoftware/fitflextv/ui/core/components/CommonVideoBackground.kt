package com.dreamsoftware.fitflextv.ui.core.components
import android.annotation.SuppressLint
import android.content.ContentResolver
import android.net.Uri
import androidx.annotation.OptIn
import androidx.annotation.RawRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.util.EventLogger
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView

@OptIn(UnstableApi::class)
@SuppressLint("OpaqueUnitKey")
@Composable
fun CommonVideoBackground(
    @RawRes videoResourceId: Int
) {
    val context = LocalContext.current
    val exoPlayer = ExoPlayer.Builder(context)
        .build()
        .apply {
            addAnalyticsListener(EventLogger())
            setMediaItem(MediaItem.fromUri(Uri.Builder().scheme(ContentResolver.SCHEME_ANDROID_RESOURCE).path(
                videoResourceId.toString()
            ).build()))
            prepare()
            repeatMode = Player.REPEAT_MODE_ALL
            playWhenReady = false
        }
    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)
    DisposableEffect(AndroidView(factory = {
        PlayerView(context).apply {
            player = exoPlayer
            useController = false
            resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
            setBackgroundColor(Color.Black.toArgb())
            hideController()
        }
    }, modifier = Modifier.fillMaxSize())) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_PAUSE -> {
                    exoPlayer.pause()
                }
                Lifecycle.Event.ON_RESUME -> {
                    exoPlayer.play()
                }
                else -> {}
            }
        }
        val lifecycle = lifecycleOwner.value.lifecycle
        lifecycle.addObserver(observer)
        onDispose {
            exoPlayer.release()
            lifecycle.removeObserver(observer)
        }
    }
}