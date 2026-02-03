package com.practice.reels.features.community.domain.repository

import com.practice.reels.core.domain.util.ResultOf
import com.practice.reels.features.community.domain.model.CommunityDetails
import com.practice.reels.features.community.domain.model.CommunityLoops
import com.practice.reels.features.community.domain.model.CommunityMembers

interface CommunityRepository {
    suspend fun getCommunityDetails(communityId: String): ResultOf<CommunityDetails, String>
    suspend fun getCommunityLoops(communityId: String): ResultOf<CommunityLoops, String>
    suspend fun getCommunityMembers(communityId: String): ResultOf<CommunityMembers, String>
}