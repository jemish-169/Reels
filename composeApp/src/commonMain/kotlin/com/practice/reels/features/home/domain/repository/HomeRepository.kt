package com.practice.reels.features.home.domain.repository

import com.practice.reels.core.domain.util.ResultOf
import com.practice.reels.features.home.domain.model.Feed

interface HomeRepository {
    suspend fun fetchFeed(deviceId: String, pageSession: String? = null): ResultOf<Feed, String>
}