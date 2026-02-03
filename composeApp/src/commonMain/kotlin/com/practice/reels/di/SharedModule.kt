package com.practice.reels.di

import com.practice.reels.features.community.data.repository.CommunityRepositoryImpl
import com.practice.reels.features.community.domain.repository.CommunityRepository
import com.practice.reels.features.community.presentation.viewmodel.CommunityViewModel
import com.practice.reels.features.home.data.repository.HomeRepositoryImpl
import com.practice.reels.features.home.domain.repository.HomeRepository
import com.practice.reels.features.home.presentation.viewmodel.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val sharedModule = module {
    single<HomeRepository> { HomeRepositoryImpl(httpClient = get()) }
    single<CommunityRepository> { CommunityRepositoryImpl(httpClient = get()) }
    viewModel {
        HomeViewModel(
            messagePasser = get(),
            homeRepository = get()
        )
    }
    viewModel { (communityId: String) ->
        CommunityViewModel(
            communityId = communityId,
            messagePasser = get(),
            communityRepository = get()
        )
    }
}