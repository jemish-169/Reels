package com.practice.reels.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable

@Composable
fun PreviewWithTheme(content: @Composable () -> Unit) {
    ReelsTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            content()
        }
    }
}
