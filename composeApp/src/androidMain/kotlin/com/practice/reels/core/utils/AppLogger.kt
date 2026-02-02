package com.practice.reels.core.utils

import android.util.Log

class AndroidAppLogger : AppLogger {
    override fun e(tag: String, message: String, throwable: Throwable?) {
        if (throwable != null) {
            Log.e(tag, message, throwable)
        } else {
            Log.e(tag, message)
        }
    }

    override fun d(tag: String, message: String) {
        Log.d(tag, message)
    }

    override fun i(tag: String, message: String) {
        Log.i(tag, message)
    }
}

actual fun createAppLogger(): AppLogger = AndroidAppLogger()
