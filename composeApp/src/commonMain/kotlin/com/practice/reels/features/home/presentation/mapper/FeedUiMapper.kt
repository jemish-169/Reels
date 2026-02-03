package com.practice.reels.features.home.presentation.mapper

import com.practice.reels.core.presentation.utils.formatCount
import com.practice.reels.core.presentation.utils.formatDuration
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
        duration = formatDuration(this.duration ?: 0),
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
