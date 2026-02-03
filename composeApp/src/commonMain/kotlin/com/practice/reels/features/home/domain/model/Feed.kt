package com.practice.reels.features.home.domain.model

data class Feed(
    val items: List<Reel>,
    val endOfFeed: Boolean,
    val expiry: Long?,
    val pageSession: String?
)

data class Reel(
    val id: String,
    val type: String,
    val videoUrl: String,
    val videoUrlM3u8: String?,
    val thumbnailUrl: String,
    val thumbnailUrlL: String?,
    val thumbnailUrlS: String?,
    val description: String?,
    val duration: Int?,
    val userName: String,
    val userProfilePic: String?,
    val isAvatar: Boolean,
    val likes: Int,
    val commentCount: Int,
    val shareCount: Int,
    val viewCount: Int,
    val groupName: String?,
    val groupId: String?,
    val loopSlug: String?,
    val communityId: String?,
    val communityName: String?,
    val communitySlug: String?,
    val communityShareUrl: String?,
    val shareUrl: String?,
    val isSparked: Boolean,
    val isWatched: Boolean
)
