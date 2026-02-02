package com.practice.reels

import androidx.compose.ui.window.ComposeUIViewController
import com.practice.reels.app.App
import com.practice.reels.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }