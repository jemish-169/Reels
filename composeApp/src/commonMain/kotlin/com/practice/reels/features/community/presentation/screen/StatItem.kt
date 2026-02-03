package com.practice.reels.features.community.presentation.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun StatItem(value: String, label: String) {
    Row {
        Text(text = value, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = label, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}
