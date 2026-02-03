package com.practice.reels.features.community.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.practice.reels.features.community.presentation.model.CommunityDetailsUi
import org.jetbrains.compose.resources.stringResource
import reels.composeapp.generated.resources.Res
import reels.composeapp.generated.resources.groups
import reels.composeapp.generated.resources.members
import reels.composeapp.generated.resources.videos

@Composable
fun StatsRow(details: CommunityDetailsUi) {
    Row(
        modifier = Modifier.padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        StatItem(value = details.memberCount, label = stringResource(Res.string.members))
        StatItem(value = details.loopCount, label = stringResource(Res.string.groups))
        StatItem(value = details.videoCount, label = stringResource(Res.string.videos))
    }
}
