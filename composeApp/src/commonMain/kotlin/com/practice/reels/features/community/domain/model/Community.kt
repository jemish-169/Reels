package com.practice.reels.features.community.domain.model

// ======================= Community Details =======================

data class CommunityDetails(
    val id: String,
    val handle: String,
    val slug: String,
    val name: String,
    val description: String?,
    val colorCode: String?,
    val textColorCode: String?,
    val dp: String?,
    val shareUrl: String?,
    val memberCount: Int,
    val loopCount: Int,
    val videoCount: Int,
    val viewCount: Int,
    val leaderName: String?,
    val leaderUsername: String?,
    val leaderProfileImage: String?,
    val isLoopCreationAllowed: Boolean
)

// ======================= Community Loops =======================

data class CommunityLoops(
    val loops: List<Loop>,
    val count: Int
)

data class Loop(
    val id: String,
    val slug: String,
    val shareUrl: String?,
    val groupId: String,
    val groupName: String,
    val groupDescription: String?,
    val colorCode: String?,
    val textColorCode: String?,
    val memberCount: Int,
    val videoCount: Int,
    val isSubscriber: Boolean,
    val isPostAllowed: Boolean,
    val latestMessageThumbnail: String?
)

// ======================= Community Members =======================

data class CommunityMembers(
    val members: List<Member>
)

data class Member(
    val id: String,
    val name: String?,
    val nickname: String?,
    val bio: String?,
    val isAvatar: Boolean,
    val profileImage: String?,
    val role: Int?,
    val isBrandSystemUser: Boolean
)
