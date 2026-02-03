package com.practice.reels.app

import androidx.compose.runtime.Composable
import com.practice.reels.core.presentation.navigation.RootNavGraph
import com.practice.reels.core.utils.MessagePasser
import com.practice.reels.theme.ReelsTheme
import org.koin.compose.koinInject


@Composable
fun App() {

    val messagePasser: MessagePasser = koinInject()

    ReelsTheme {
        RootNavGraph(
            messagePasser = messagePasser
        )
    }
}