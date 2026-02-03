package com.practice.reels.features.community.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practice.reels.core.presentation.state.AppState
import com.practice.reels.features.community.presentation.components.TopAppBar
import com.practice.reels.features.community.presentation.model.CommunityDetailsUi
import com.practice.reels.features.community.presentation.model.CommunityLoopsUi
import com.practice.reels.features.community.presentation.model.CommunityMembersUi
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import reels.composeapp.generated.resources.Res
import reels.composeapp.generated.resources.groups
import reels.composeapp.generated.resources.members

@Composable
fun CommunityContent(
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
        Spacer(modifier = Modifier.height(12.dp))

        CommunityInfo(details = details)
        Spacer(modifier = Modifier.height(12.dp))

        StatsRow(details = details)
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .wrapContentWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            tabs.forEachIndexed { index, title ->
                val isSelected = pagerState.currentPage == index
                Column(
                    modifier = Modifier
                        .wrapContentWidth()
                        .clickable {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = title,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 15.sp
                    )
                }
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
