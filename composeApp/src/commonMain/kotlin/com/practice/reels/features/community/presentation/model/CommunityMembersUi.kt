package com.practice.reels.features.community.presentation.model

data class CommunityMembersUi(
    val members: List<MemberUi>
)

data class MemberUi(
    val id: String,
    val username: String
)
