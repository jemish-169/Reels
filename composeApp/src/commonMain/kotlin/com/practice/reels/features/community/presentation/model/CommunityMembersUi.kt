package com.practice.reels.features.community.presentation.model

data class CommunityMembersUi(
    val members: List<MemberUi>
)

data class MemberUi(
    val id: String,
    val nickname: String?,
    val name: String?,
    val bio: String?,
    val profileImage: String?,
    val isAvatar: Boolean,
    val role: Int?,
    val isBrandSystemUser: Boolean
)
