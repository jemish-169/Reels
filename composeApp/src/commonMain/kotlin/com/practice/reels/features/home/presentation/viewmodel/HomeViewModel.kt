package com.practice.reels.features.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.reels.core.domain.util.DataError
import com.practice.reels.core.domain.util.onError
import com.practice.reels.core.domain.util.onSuccess
import com.practice.reels.core.presentation.state.AppState
import com.practice.reels.core.utils.MessagePasser
import com.practice.reels.features.home.domain.repository.HomeRepository
import com.practice.reels.features.home.presentation.mapper.toUi
import com.practice.reels.features.home.presentation.model.FeedUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val DEVICE_ID = "JEMISH_KHUNT"
private const val LOAD_NEXT_THRESHOLD = 2

class HomeViewModel(
    private val messagePasser: MessagePasser,
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val _feedState = MutableStateFlow<AppState<FeedUi, String>>(value = AppState.Idle)
    val feedState: StateFlow<AppState<FeedUi, String>> = _feedState.asStateFlow()

    private val _isLoadingNext = MutableStateFlow(false)
    val isLoadingNext: StateFlow<Boolean> = _isLoadingNext.asStateFlow()

    init {
        fetchFeed()
    }

    fun fetchFeed(deviceId: String = DEVICE_ID) {
        viewModelScope.launch {
            try {
                _feedState.update { AppState.Loading }

                homeRepository.fetchFeed(deviceId, pageSession = null)
                    .onSuccess { res ->
                        val feedUi = res.toUi()
                        _feedState.update { AppState.Success(message = feedUi) }
                    }.onError { error ->
                        messagePasser.sendMessage(msg = error)
                        _feedState.update { AppState.Error(error) }
                    }
            } catch (e: Exception) {
                val message = e.message ?: DataError.UNKNOWN.value
                messagePasser.sendMessage(msg = message)
                _feedState.update { AppState.Error(error = message) }
            }
        }
    }

    /**
     * Call when user is about to reach the end of the list (e.g. within [LOAD_NEXT_THRESHOLD] items).
     * Fetches the next page only when [endOfFeed] is false and we have a [pageSession].
     */
    fun loadNextPageIfNeeded(currentPageIndex: Int, totalItems: Int) {
        if (_isLoadingNext.value) return

        val current = _feedState.value
        if (current !is AppState.Success) return

        val feed = current.message
        if (feed.endOfFeed) return
        if (feed.pageSession.isNullOrBlank()) return
        if (totalItems == 0) return
        if (currentPageIndex < totalItems - LOAD_NEXT_THRESHOLD) return

        viewModelScope.launch {
            _isLoadingNext.value = true
            try {
                homeRepository.fetchFeed(DEVICE_ID, pageSession = feed.pageSession)
                    .onSuccess { nextFeed ->
                        val nextUi = nextFeed.toUi()
                        _feedState.update { state ->
                            when (state) {
                                is AppState.Success -> AppState.Success(
                                    message = FeedUi(
                                        items = state.message.items + nextUi.items,
                                        endOfFeed = nextUi.endOfFeed,
                                        pageSession = nextUi.pageSession
                                    )
                                )
                                else -> state
                            }
                        }
                    }
                    .onError { error ->
                        messagePasser.sendMessage(msg = error)
                    }
            } catch (e: Exception) {
                messagePasser.sendMessage(msg = e.message ?: DataError.UNKNOWN.value)
            } finally {
                _isLoadingNext.value = false
            }
        }
    }
}