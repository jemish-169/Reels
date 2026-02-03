package com.practice.reels.di

import com.practice.reels.core.utils.MessagePasser
import org.koin.dsl.module

val appStartModule = module(createdAtStart = true) {
    single { MessagePasser() }
}