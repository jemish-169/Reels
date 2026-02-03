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

class HomeViewModel(
    private val messagePasser: MessagePasser,
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val _feedState = MutableStateFlow<AppState<FeedUi, String>>(value = AppState.Idle)
    val feedState: StateFlow<AppState<FeedUi, String>> = _feedState.asStateFlow()

    init {
        fetchFeed("YOUR_DEVICE_ID")
    }

    fun fetchFeed(deviceId: String) {
        viewModelScope.launch {
            try {
                _feedState.update { AppState.Loading }

                homeRepository.fetchFeed(deviceId)
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
}