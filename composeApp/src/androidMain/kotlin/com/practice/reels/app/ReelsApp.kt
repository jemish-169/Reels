package com.practice.reels.app

import android.app.Application
import com.practice.reels.di.initKoin
import org.koin.android.ext.koin.androidContext

class ReelsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@ReelsApp)
        }
    }
}