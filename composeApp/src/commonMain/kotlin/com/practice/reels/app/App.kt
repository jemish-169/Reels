package com.practice.reels.app

import androidx.compose.runtime.Composable
import com.practice.reels.core.presentation.navigation.RootNavGraph
import com.practice.reels.core.utils.MessagePasser
import com.practice.reels.theme.Reelsheme
import org.koin.compose.koinInject


@Composable
fun App() {

    val messagePasser: MessagePasser = koinInject()

    Reelsheme {
        RootNavGraph(
            onFinish = {}, // No need when user is seeing Bottom-Nav bar (in AppNavGraph)
            messagePasser = messagePasser,
        )
    }
}