package com.practice.reels.features.home.presentation.screen.component

import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

@Composable
actual fun ReelVideoPlayer(
    videoUrl: String?,
    videoUrlM3u8: String?,
    thumbnailUrl: String,
    modifier: Modifier,
    isPlaying: Boolean
) {
    val context = LocalContext.current
    val url = videoUrlM3u8?.takeIf { it.isNotBlank() } ?: videoUrl?.takeIf { it.isNotBlank() }

    if (!url.isNullOrBlank()) {
        val player = remember {
            ExoPlayer.Builder(context).build().apply {
                repeatMode = Player.REPEAT_MODE_ONE
            }
        }

        val lifecycleOwner = LocalLifecycleOwner.current
        DisposableEffect(lifecycleOwner) {
            val observer = LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_STOP) player.pause()
            }
            lifecycleOwner.lifecycle.addObserver(observer)
            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
                player.release()
            }
        }

        LaunchedEffect(url) {
            val mediaItem = MediaItem.fromUri(url)
            player.setMediaItem(mediaItem)
            player.prepare()
        }

        LaunchedEffect(isPlaying) {
            if (isPlaying) {
                player.play()
            } else {
                player.pause()
            }
        }

        AndroidView(
            factory = {
                PlayerView(context).apply {
                    useController = false
                    this.player = player
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                }
            },
            modifier = modifier,
            update = { playerView ->
                playerView.useController = false
                playerView.player = player
            }
        )
    }
}