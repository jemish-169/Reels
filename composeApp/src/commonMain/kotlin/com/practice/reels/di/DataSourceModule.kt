package com.practice.reels.di

import com.practice.reels.core.data.utils.HttpClientFactory
import org.koin.dsl.module

val dataSourceModule = module {
    single { HttpClientFactory.create(get()) }
}