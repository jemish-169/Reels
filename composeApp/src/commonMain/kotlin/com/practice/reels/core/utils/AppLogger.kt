package com.practice.reels.core.utils

expect fun createAppLogger(): AppLogger

interface AppLogger {
    fun e(tag: String, message: String, throwable: Throwable? = null)
    fun d(tag: String, message: String)
    fun i(tag: String, message: String)
}

object Log {
    private val logger = createAppLogger()

    fun e(message: String, tag: String = "TAG", throwable: Throwable? = null) {
        logger.e(tag, message, throwable)
    }

    fun d(message: String, tag: String = "TAG") {
        logger.d(tag, message)
    }

    fun i(message: String, tag: String = "TAG") {
        logger.i(tag, message)
    }
}