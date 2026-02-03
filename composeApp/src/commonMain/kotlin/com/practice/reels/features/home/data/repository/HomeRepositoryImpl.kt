package com.practice.reels.features.home.data.repository

import com.practice.reels.core.data.utils.APIConstants
import com.practice.reels.core.data.utils.EmptyParams
import com.practice.reels.core.data.utils.apiCall
import com.practice.reels.core.data.utils.safeCall
import com.practice.reels.core.domain.util.DataError
import com.practice.reels.core.domain.util.ResultOf
import com.practice.reels.core.domain.util.map
import com.practice.reels.features.home.data.mapper.toFeed
import com.practice.reels.features.home.data.model.FeedRequest
import com.practice.reels.features.home.data.model.FeedResponse
import com.practice.reels.features.home.domain.model.Feed
import com.practice.reels.features.home.domain.repository.HomeRepository
import io.ktor.client.HttpClient
import io.ktor.http.HttpMethod

class HomeRepositoryImpl(
    private val httpClient: HttpClient
) : HomeRepository {

    override suspend fun fetchFeed(deviceId: String): ResultOf<Feed, String> {
        return try {
            val result = safeCall<FeedResponse> {
                apiCall<EmptyParams, EmptyParams, FeedRequest>(
                    urlParams = EmptyParams,
                    headerParams = EmptyParams,
                    bodyParams = FeedRequest(deviceId = deviceId, type = 1),
                    serviceCall = APIConstants.FEED_HOME,
                    client = httpClient,
                    httpMethod = HttpMethod.Post
                )
            }

            result.map { response -> response.toFeed() }
        } catch (e: Exception) {
            ResultOf.Error(e.message ?: DataError.UNKNOWN.value)
        }
    }
}
