package com.practice.reels.features.community.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.stringResource
import reels.composeapp.generated.resources.Res
import reels.composeapp.generated.resources.back_icon

@Composable
fun TopAppBar(
    title: String,
    isBackEnabled: Boolean = false,
    onBackClick: () -> Unit = {},
    trailingComposable: @Composable () -> Unit = {},
    trailingIcon: ImageVector? = null,
    trailingIconDesc: String? = null,
    onTrailingIconClick: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(35.dp)
    ) {
        if (isBackEnabled) {
            HeaderIcon(
                iconString = stringResource(Res.string.back_icon),
                iconRes = Icons.Default.ArrowBackIosNew,
                onIconClick = onBackClick
            )
            Spacer(modifier = Modifier.width(10.dp))
        }
        Text(
            text = title,
            fontWeight = FontWeight.SemiBold,
            fontSize = 22.sp,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.weight(1f))

        if (trailingIcon != null && trailingIconDesc != null) {
            HeaderIcon(
                iconString = trailingIconDesc,
                iconRes = trailingIcon,
                onIconClick = onTrailingIconClick
            )
        } else {
            trailingComposable()
        }
    }
}