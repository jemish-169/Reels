package com.practice.reels.features.community.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.practice.reels.core.presentation.state.AppState
import com.practice.reels.features.community.presentation.model.CommunityMembersUi
import com.practice.reels.features.community.presentation.model.MemberUi
import org.jetbrains.compose.resources.stringResource
import reels.composeapp.generated.resources.Res
import reels.composeapp.generated.resources.no_data_found

@Composable
fun MembersTabContent(membersState: AppState<CommunityMembersUi, String>) {
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
                    items(members, key = { it.id }) { member: MemberUi ->
                        MemberItem(member = member)
                    }
                }
            }
        }
    }
}
