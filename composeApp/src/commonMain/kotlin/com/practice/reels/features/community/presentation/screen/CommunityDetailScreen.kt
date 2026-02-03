package com.practice.reels.features.community.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.practice.reels.core.presentation.state.AppState
import com.practice.reels.core.presentation.utils.parseColor
import com.practice.reels.features.community.presentation.components.TopAppBar
import com.practice.reels.features.community.presentation.model.CommunityDetailsUi
import com.practice.reels.features.community.presentation.model.CommunityLoopsUi
import com.practice.reels.features.community.presentation.model.CommunityMembersUi
import com.practice.reels.features.community.presentation.model.LoopCollaboratorUi
import com.practice.reels.features.community.presentation.model.LoopUi
import com.practice.reels.features.community.presentation.model.MemberUi
import com.practice.reels.features.community.presentation.viewmodel.CommunityViewModel
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf
import reels.composeapp.generated.resources.Res
import reels.composeapp.generated.resources.community_banner
import reels.composeapp.generated.resources.groups
import reels.composeapp.generated.resources.join
import reels.composeapp.generated.resources.members
import reels.composeapp.generated.resources.no_data_found
import reels.composeapp.generated.resources.others
import reels.composeapp.generated.resources.posted
import reels.composeapp.generated.resources.share
import reels.composeapp.generated.resources.videos

@Composable
fun CommunityDetailScreen(
    communityId: String,
    paddingValues: PaddingValues,
    onBackClick: () -> Unit,
    viewModel: CommunityViewModel = koinViewModel { parametersOf(communityId) }
) {
    val detailsState by viewModel.detailsState.collectAsStateWithLifecycle()
    val loopsState by viewModel.loopsState.collectAsStateWithLifecycle()
    val membersState by viewModel.membersState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        when (val state = detailsState) {
            is AppState.Idle, is AppState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is AppState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = state.error, color = MaterialTheme.colorScheme.error)
                }
            }

            is AppState.Success -> {
                CommunityContent(
                    details = state.message,
                    loopsState = loopsState,
                    membersState = membersState,
                    onBackClick = onBackClick
                )
            }
        }
    }
}

@Composable
private fun CommunityContent(
    details: CommunityDetailsUi,
    loopsState: AppState<CommunityLoopsUi, String>,
    membersState: AppState<CommunityMembersUi, String>,
    onBackClick: () -> Unit
) {
    val pagerState = rememberPagerState(initialPage = 0) { 2 }
    val coroutineScope = rememberCoroutineScope()
    val groupsLabel = stringResource(resource = Res.string.groups)
    val membersLabel = stringResource(resource = Res.string.members)
    val tabs = listOf(groupsLabel, membersLabel)

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = details.handle,
            isBackEnabled = true,
            onBackClick = onBackClick
        )
        Spacer(modifier = Modifier.height(4.dp))

        CommunityHeader(details = details)
        Spacer(modifier = Modifier.height(16.dp))

        CommunityInfo(details = details)
        Spacer(modifier = Modifier.height(16.dp))

        StatsRow(details = details)
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            tabs.forEachIndexed { index, title ->
                val isSelected = pagerState.currentPage == index
                Text(
                    text = title,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                    color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                )
                if (index < tabs.lastIndex) {
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { page ->
            when (page) {
                0 -> GroupsTabContent(loopsState = loopsState)
                1 -> MembersTabContent(membersState = membersState)
            }
        }
    }
}

@Composable
private fun CommunityHeader(details: CommunityDetailsUi) {
    Box(modifier = Modifier.fillMaxWidth()) {
        if (!details.banner.isNullOrEmpty()) {
            AsyncImage(
                model = details.banner,
                contentDescription = stringResource(Res.string.community_banner),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentScale = ContentScale.Crop
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(
                        color = details.colorCode?.let { parseColor(it) }
                            ?: MaterialTheme.colorScheme.primaryContainer
                    )
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .offset(x = 16.dp, y = 40.dp)
                .size(80.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            if (!details.dp.isNullOrEmpty()) {
                AsyncImage(
                    model = details.dp,
                    contentDescription = details.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = details.colorCode?.let { parseColor(it) }
                                ?: MaterialTheme.colorScheme.primary
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = details.name.take(2).uppercase(),
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(y = 16.dp)
                .padding(end = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(horizontal = 20.dp, vertical = 8.dp)
            ) {
                Text(
                    text = stringResource(Res.string.join),
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                )
            }

            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = stringResource(Res.string.share),
                    modifier = Modifier.size(18.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(48.dp))
}

@Composable
private fun CommunityInfo(details: CommunityDetailsUi) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = details.name,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        details.description?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun StatsRow(details: CommunityDetailsUi) {
    Row(
        modifier = Modifier.padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        StatItem(value = details.memberCount, label = stringResource(Res.string.members))
        StatItem(value = details.loopCount, label = stringResource(Res.string.groups))
        StatItem(value = details.videoCount, label = stringResource(Res.string.videos))
    }
}

@Composable
private fun StatItem(value: String, label: String) {
    Row {
        Text(text = value, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = label, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
private fun GroupsTabContent(loopsState: AppState<CommunityLoopsUi, String>) {
    when (loopsState) {
        is AppState.Idle, is AppState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is AppState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = loopsState.error, color = MaterialTheme.colorScheme.error)
            }
        }

        is AppState.Success -> {
            val loops = loopsState.message.loops
            if (loops.isEmpty()) {
                EmptyState(message = stringResource(Res.string.no_data_found))
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(loops, key = { it.id }) { loop ->
                        GroupItem(loop = loop)
                    }
                }
            }
        }
    }
}

@Composable
private fun MembersTabContent(membersState: AppState<CommunityMembersUi, String>) {
    when (membersState) {
        is AppState.Idle, is AppState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is AppState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = membersState.error, color = MaterialTheme.colorScheme.error)
            }
        }

        is AppState.Success -> {
            val members = membersState.message.members
            if (members.isEmpty()) {
                EmptyState(message = stringResource(Res.string.no_data_found))
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(members, key = { it.id }) { member ->
                        MemberItem(member = member)
                    }
                }
            }
        }
    }
}

@Composable
private fun GroupItem(loop: LoopUi) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = loop.groupName,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )

            loop.latestMessageOwner?.let { owner ->
                Text(
                    text = "$owner ${stringResource(Res.string.posted)}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            if (loop.collaborators.isNotEmpty()) {
                Row(
                    modifier = Modifier.padding(top = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy((-8).dp)
                ) {
                    loop.collaborators.take(3).forEach { collaborator ->
                        CollaboratorAvatar(collaborator = collaborator)
                    }
                    if (loop.collaborators.size > 3) {
                        Text(
                            text = " + ${loop.collaborators.size - 3} ${stringResource(Res.string.others)}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(start = 12.dp)
                        )
                    }
                }
            }

            loop.groupDescription?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        loop.latestMessageThumbnail?.let {
            AsyncImage(
                model = it,
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp, 80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
private fun CollaboratorAvatar(collaborator: LoopCollaboratorUi) {
    Box(
        modifier = Modifier
            .size(24.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primaryContainer),
        contentAlignment = Alignment.Center
    ) {
        if (!collaborator.profileImage.isNullOrEmpty() && !collaborator.isAvatar) {
            AsyncImage(
                model = collaborator.profileImage,
                contentDescription = collaborator.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        } else {
            Text(
                text = (collaborator.username ?: collaborator.name ?: "?").take(1).uppercase(),
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Composable
private fun MemberItem(member: MemberUi) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            if (!member.profileImage.isNullOrEmpty() && !member.isAvatar) {
                AsyncImage(
                    model = member.profileImage,
                    contentDescription = member.nickname,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            } else {
                Text(
                    text = (member.nickname ?: member.name ?: "?").take(1).uppercase(),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            member.nickname?.let {
                Text(
                    text = "@$it",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }
            member.name?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            member.bio?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
private fun EmptyState(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

