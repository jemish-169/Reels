package com.practice.reels.features.community.presentation.screen

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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.practice.reels.core.presentation.state.AppState
import com.practice.reels.features.community.presentation.model.CommunityDetailsUi
import com.practice.reels.features.community.presentation.model.CommunityLoopsUi
import com.practice.reels.features.community.presentation.model.CommunityMembersUi
import com.practice.reels.features.community.presentation.viewmodel.CommunityViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

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
