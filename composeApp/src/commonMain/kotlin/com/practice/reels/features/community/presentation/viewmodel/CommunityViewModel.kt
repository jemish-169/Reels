package com.practice.reels.features.community.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.reels.core.domain.util.DataError
import com.practice.reels.core.domain.util.onError
import com.practice.reels.core.domain.util.onSuccess
import com.practice.reels.core.presentation.state.AppState
import com.practice.reels.core.utils.MessagePasser
import com.practice.reels.features.community.domain.repository.CommunityRepository
import com.practice.reels.features.community.presentation.mapper.toUi
import com.practice.reels.features.community.presentation.model.CommunityDetailsUi
import com.practice.reels.features.community.presentation.model.CommunityLoopsUi
import com.practice.reels.features.community.presentation.model.CommunityMembersUi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class CommunityViewModel(
    private val communityId: String,
    private val messagePasser: MessagePasser,
    private val communityRepository: CommunityRepository
) : ViewModel() {

    private val _detailsState = MutableStateFlow<AppState<CommunityDetailsUi, String>>(value = AppState.Idle)
    val detailsState: StateFlow<AppState<CommunityDetailsUi, String>> = _detailsState.asStateFlow()

    private val _loopsState = MutableStateFlow<AppState<CommunityLoopsUi, String>>(value = AppState.Idle)
    val loopsState: StateFlow<AppState<CommunityLoopsUi, String>> = _loopsState.asStateFlow()

    private val _membersState = MutableStateFlow<AppState<CommunityMembersUi, String>>(value = AppState.Idle)
    val membersState: StateFlow<AppState<CommunityMembersUi, String>> = _membersState.asStateFlow()

    init {
        fetchAllCommunityData()
    }

    private fun fetchAllCommunityData() {
        viewModelScope.launch {
            _detailsState.update { AppState.Loading }
            _loopsState.update { AppState.Loading }
            _membersState.update { AppState.Loading }

            supervisorScope {
                val detailsJob = async { fetchCommunityDetails() }
                val loopsJob = async { fetchCommunityLoops() }
                val membersJob = async { fetchCommunityMembers() }

                detailsJob.await()
                loopsJob.await()
                membersJob.await()
            }
        }
    }

    private suspend fun fetchCommunityDetails() {
        try {
            communityRepository.getCommunityDetails(communityId)
                .onSuccess { res ->
                    _detailsState.update { AppState.Success(message = res.toUi()) }
                }.onError { error ->
                    messagePasser.sendMessage(msg = error)
                    _detailsState.update { AppState.Error(error = error) }
                }
        } catch (e: Exception) {
            val message = e.message ?: DataError.UNKNOWN.value
            messagePasser.sendMessage(msg = message)
            _detailsState.update { AppState.Error(error = message) }
        }
    }

    private suspend fun fetchCommunityLoops() {
        try {
            communityRepository.getCommunityLoops(communityId)
                .onSuccess { res ->
                    _loopsState.update { AppState.Success(message = res.toUi()) }
                }.onError { error ->
                    messagePasser.sendMessage(msg = error)
                    _loopsState.update { AppState.Error(error = error) }
                }
        } catch (e: Exception) {
            val message = e.message ?: DataError.UNKNOWN.value
            messagePasser.sendMessage(msg = message)
            _loopsState.update { AppState.Error(error = message) }
        }
    }

    private suspend fun fetchCommunityMembers() {
        try {
            communityRepository.getCommunityMembers(communityId)
                .onSuccess { res ->
                    _membersState.update { AppState.Success(message = res.toUi()) }
                }.onError { error ->
                    messagePasser.sendMessage(msg = error)
                    _membersState.update { AppState.Error(error = error) }
                }
        } catch (e: Exception) {
            val message = e.message ?: DataError.UNKNOWN.value
            messagePasser.sendMessage(msg = message)
            _membersState.update { AppState.Error(error = message) }
        }
    }
}
