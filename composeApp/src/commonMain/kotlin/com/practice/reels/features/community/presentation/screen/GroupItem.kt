package com.practice.reels.features.community.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil3.compose.AsyncImage
import com.practice.reels.features.community.presentation.model.LoopUi
import org.jetbrains.compose.resources.stringResource
import reels.composeapp.generated.resources.Res
import reels.composeapp.generated.resources.others
import reels.composeapp.generated.resources.posted
import reels.composeapp.generated.resources.recent_posts

@Composable
fun GroupItem(loop: LoopUi) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = loop.groupName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .background(
                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(10.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        if (loop.latestMessageOwner != null || loop.latestMessageAt != null) {
                            Text(
                                text = buildString {
                                    loop.latestMessageOwner?.let { append(it) }
                                    if (loop.latestMessageOwner != null) append(" ${stringResource(Res.string.posted)}")
                                    if (loop.latestMessageAt != null) {
                                        if (loop.latestMessageOwner != null) append(" • ")
                                        append(loop.latestMessageAt)
                                    }
                                },
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        if (loop.collaborators.isNotEmpty()) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy((-8).dp)
                            ) {
                                loop.collaborators.take(3).forEach { collaborator ->
                                    CollaboratorAvatar(collaborator = collaborator)
                                }
                                val firstName = loop.collaborators.firstOrNull()?.username
                                    ?: loop.collaborators.firstOrNull()?.name
                                    ?: ""
                                val othersCount = (loop.collaborators.size - 1).coerceAtLeast(0)
                                Text(
                                    text = if (othersCount > 0) {
                                        "$firstName + $othersCount ${stringResource(Res.string.others)}"
                                    } else {
                                        firstName.ifEmpty { stringResource(Res.string.others) }
                                    },
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.padding(start = 12.dp)
                                )
                            }
                        }

                        loop.groupDescription?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            val thumbnails = if (loop.latestMessageThumbnails.isNotEmpty()) {
                loop.latestMessageThumbnails
            } else {
                loop.latestMessageThumbnail?.let { listOf(it) }.orEmpty()
            }
            if (thumbnails.isNotEmpty()) {
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = stringResource(Res.string.recent_posts),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Box(contentAlignment = Alignment.TopEnd) {
                        val stacked = thumbnails.take(2)
                        stacked.forEachIndexed { index, url ->
                            val isFront = index == stacked.lastIndex
                            AsyncImage(
                                model = url,
                                contentDescription = null,
                                modifier = Modifier
                                    .zIndex(if (isFront) 1f else 0f)
                                    .padding(
                                        start = if (isFront) 0.dp else 4.dp,
                                        top = if (isFront) 0.dp else 4.dp
                                    )
                                    .size(width = 72.dp, height = 96.dp)
                                    .clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }
        }
    }
}
