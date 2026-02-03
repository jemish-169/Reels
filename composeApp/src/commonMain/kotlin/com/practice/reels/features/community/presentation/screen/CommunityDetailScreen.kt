package com.practice.reels.features.community.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.practice.reels.core.presentation.state.AppState
import com.practice.reels.features.community.presentation.viewmodel.CommunityViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun CommunityDetailScreen(
    communityId: String,
    paddingValues: PaddingValues,
    viewModel: CommunityViewModel = koinViewModel { parametersOf(communityId) }
) {
    val detailsState by viewModel.detailsState.collectAsStateWithLifecycle()
    val loopsState by viewModel.loopsState.collectAsStateWithLifecycle()
    val membersState by viewModel.membersState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Community: $communityId",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(text = "Details:", style = MaterialTheme.typography.titleMedium)
        StateContent(state = detailsState) { details ->
            Text(text = "Name: ${details.name}")
        }

        Text(text = "Loops:", style = MaterialTheme.typography.titleMedium)
        StateContent(state = loopsState) { loops ->
            Text(text = "Count: ${loops.loops.size}")
        }

        Text(text = "Members:", style = MaterialTheme.typography.titleMedium)
        StateContent(state = membersState) { members ->
            Text(text = "Count: ${members.members.size}")
        }
    }
}

@Composable
private fun <T> StateContent(
    state: AppState<T, String>,
    content: @Composable (T) -> Unit
) {
    when (state) {
        is AppState.Idle -> {}
        is AppState.Loading -> CircularProgressIndicator()
        is AppState.Success -> content(state.message)
        is AppState.Error -> Text(text = "Error: ${state.error}")
    }
}
