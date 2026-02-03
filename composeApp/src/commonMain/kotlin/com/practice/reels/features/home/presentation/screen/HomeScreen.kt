package com.practice.reels.features.home.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.practice.reels.core.presentation.state.AppState
import com.practice.reels.features.home.presentation.screen.component.ReelListItem
import com.practice.reels.features.home.presentation.viewmodel.HomeViewModel
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import reels.composeapp.generated.resources.Res
import reels.composeapp.generated.resources.error_with_message

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    onReelClick: (communityId: String) -> Unit,
    viewModel: HomeViewModel = koinViewModel()
) {
    val feedState by viewModel.feedState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        when (val state = feedState) {
            is AppState.Idle -> {}
            is AppState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            is AppState.Success -> {
                val feedUi = state.message
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(
                        items = feedUi.items,
                        key = { it.id }
                    ) { reel ->
                        ReelListItem(
                            reel = reel,
                            onClick = {
                                reel.communityId?.let { communityId ->
                                    onReelClick(reel.communityId)
                                }
                            }
                        )
                    }
                }
            }

            is AppState.Error -> {
                Text(
                    text = stringResource(Res.string.error_with_message, state.error),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}