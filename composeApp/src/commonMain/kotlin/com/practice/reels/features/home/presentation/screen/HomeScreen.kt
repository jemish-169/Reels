package com.practice.reels.features.home.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.practice.reels.core.presentation.state.AppState
import com.practice.reels.features.home.presentation.screen.component.ReelPageItem
import com.practice.reels.features.home.presentation.viewmodel.HomeViewModel
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import reels.composeapp.generated.resources.Res
import reels.composeapp.generated.resources.error_with_message
import reels.composeapp.generated.resources.no_data_found

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
                if (feedUi.items.isEmpty()) {
                    Text(
                        text = stringResource(Res.string.no_data_found),
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    val pagerState = rememberPagerState(
                        initialPage = 0,
                        pageCount = { feedUi.items.size }
                    )
                    LaunchedEffect(pagerState.currentPage, feedUi.items.size, feedUi.endOfFeed) {
                        viewModel.loadNextPageIfNeeded(
                            currentPageIndex = pagerState.currentPage,
                            totalItems = feedUi.items.size
                        )
                    }
                    VerticalPager(
                        state = pagerState,
                        modifier = Modifier.fillMaxSize(),
                        beyondViewportPageCount = 1,
                        userScrollEnabled = true
                    ) { page ->
                        val reel = feedUi.items[page]
                        val isCurrentPage = page == pagerState.currentPage
                        ReelPageItem(
                            reel = reel,
                            isCurrentPage = isCurrentPage,
                            onCommunityClick = {
                                reel.communityId?.let { id -> onReelClick(id) }
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