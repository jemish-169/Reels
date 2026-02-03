package com.practice.reels.features.community.presentation.model

data class CommunityDetailsUi(
    val id: String,
    val name: String,
    val handle: String,
    val slug: String,
    val description: String?,
    val colorCode: String?,
    val textColorCode: String?,
    val banner: String?,
    val dp: String?,
    val shareUrl: String?,
    val memberCount: String,
    val loopCount: String,
    val videoCount: String,
    val viewCount: String,
    val isLoopCreationAllowed: Boolean,
    val isCommunityJoinRequested: Boolean,
    val leaderName: String?,
    val leaderUsername: String?,
    val leaderProfileImage: String?,
    val leaderIsAvatar: Boolean
)
