package com.practice.reels.di

import com.practice.reels.core.presentation.viewmodel.AppViewModel
import com.practice.reels.core.utils.MessagePasser
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appStartModule = module(createdAtStart = true) {
    viewModel { AppViewModel() }
    single { MessagePasser() }
}