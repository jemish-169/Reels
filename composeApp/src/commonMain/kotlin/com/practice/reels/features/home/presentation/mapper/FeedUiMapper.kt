package com.practice.reels.features.home.presentation.mapper

import com.practice.reels.features.home.domain.model.Feed
import com.practice.reels.features.home.domain.model.Reel
import com.practice.reels.features.home.presentation.model.FeedUi
import com.practice.reels.features.home.presentation.model.ReelUi

fun Feed.toUi(): FeedUi {
    return FeedUi(
        items = this.items.map { it.toUi() },
        endOfFeed = this.endOfFeed
    )
}

fun Reel.toUi(): ReelUi {
    return ReelUi(
        id = this.id,
        type = this.type,
        videoUrl = this.videoUrl,
        videoUrlM3u8 = this.videoUrlM3u8,
        thumbnailUrl = this.thumbnailUrl,
        description = this.description,
        duration = formatDuration(this.duration),
        userName = this.userName,
        userProfilePic = this.userProfilePic,
        isAvatar = this.isAvatar,
        likes = formatCount(this.likes),
        commentCount = formatCount(this.commentCount),
        shareCount = formatCount(this.shareCount),
        viewCount = formatCount(this.viewCount),
        groupName = this.groupName,
        loopSlug = this.loopSlug,
        communityId = this.communityId,
        communityName = this.communityName,
        communitySlug = this.communitySlug,
        shareUrl = this.shareUrl,
        isSparked = this.isSparked,
        isWatched = this.isWatched
    )
}

private fun formatCount(count: Int): String {
    return when {
        count >= 1_000_000 -> "${count / 1_000_000}M"
        count >= 1_000 -> "${count / 1_000}K"
        else -> count.toString()
    }
}

private fun formatDuration(seconds: Int?): String {
    if (seconds == null || seconds <= 0) return "0:00"
    val minutes = seconds / 60
    val secs = seconds % 60
    return "$minutes:${secs.toString().padStart(2, '0')}"
}
