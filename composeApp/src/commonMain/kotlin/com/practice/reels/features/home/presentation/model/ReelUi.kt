package com.practice.reels.features.home.presentation.model

data class ReelUi(
    val id: String,
    val type: String,
    val videoUrl: String,
    val videoUrlM3u8: String?,
    val thumbnailUrl: String,
    val description: String?,
    val duration: String,
    val userName: String,
    val userProfilePic: String?,
    val isAvatar: Boolean,
    val likes: String,
    val commentCount: String,
    val shareCount: String,
    val viewCount: String,
    val groupName: String?,
    val loopSlug: String?,
    val communityId: String?,
    val communityName: String?,
    val communitySlug: String?,
    val shareUrl: String?,
    val isSparked: Boolean,
    val isWatched: Boolean
)
