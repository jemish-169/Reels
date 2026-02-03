package com.practice.reels.features.community.data.repository

import com.practice.reels.core.data.utils.APIConstants
import com.practice.reels.core.data.utils.EmptyParams
import com.practice.reels.core.data.utils.apiCall
import com.practice.reels.core.data.utils.safeCall
import com.practice.reels.core.domain.util.DataError
import com.practice.reels.core.domain.util.ResultOf
import com.practice.reels.core.domain.util.map
import com.practice.reels.features.community.data.mapper.toDomain
import com.practice.reels.features.community.data.model.CommunityDetailsResponse
import com.practice.reels.features.community.data.model.CommunityIdParams
import com.practice.reels.features.community.data.model.CommunityLoopsResponse
import com.practice.reels.features.community.data.model.CommunityMembersResponse
import com.practice.reels.features.community.domain.model.CommunityDetails
import com.practice.reels.features.community.domain.model.CommunityLoops
import com.practice.reels.features.community.domain.model.CommunityMembers
import com.practice.reels.features.community.domain.repository.CommunityRepository
import io.ktor.client.HttpClient
import io.ktor.http.HttpMethod

class CommunityRepositoryImpl(
    private val httpClient: HttpClient
) : CommunityRepository {

    override suspend fun getCommunityDetails(communityId: String): ResultOf<CommunityDetails, String> {
        return try {
            val result = safeCall<CommunityDetailsResponse> {
                apiCall(
                    urlParams = CommunityIdParams(community_id = communityId),
                    bodyParams = EmptyParams,
                    serviceCall = APIConstants.COMMUNITY_DETAILS,
                    client = httpClient,
                    httpMethod = HttpMethod.Get
                )
            }
            result.map { it.toDomain() }
        } catch (e: Exception) {
            ResultOf.Error(e.message ?: DataError.UNKNOWN.value)
        }
    }

    override suspend fun getCommunityLoops(communityId: String): ResultOf<CommunityLoops, String> {
        return try {
            val result = safeCall<CommunityLoopsResponse> {
                apiCall(
                    urlParams = CommunityIdParams(community_id = communityId),
                    bodyParams = EmptyParams,
                    serviceCall = APIConstants.COMMUNITY_LOOPS,
                    client = httpClient,
                    httpMethod = HttpMethod.Get
                )
            }
            result.map { it.toDomain() }
        } catch (e: Exception) {
            ResultOf.Error(e.message ?: DataError.UNKNOWN.value)
        }
    }

    override suspend fun getCommunityMembers(communityId: String): ResultOf<CommunityMembers, String> {
        return try {
            val result = safeCall<CommunityMembersResponse> {
                apiCall(
                    urlParams = CommunityIdParams(community_id = communityId),
                    bodyParams = EmptyParams,
                    serviceCall = APIConstants.COMMUNITY_MEMBERS,
                    client = httpClient,
                    httpMethod = HttpMethod.Get
                )
            }
            result.map { it.toDomain() }
        } catch (e: Exception) {
            ResultOf.Error(e.message ?: DataError.UNKNOWN.value)
        }
    }
}