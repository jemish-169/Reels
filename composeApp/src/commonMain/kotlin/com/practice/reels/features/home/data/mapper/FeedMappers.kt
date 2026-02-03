package com.practice.reels.features.home.data.mapper

import com.practice.reels.features.home.data.model.FeedDataDto
import com.practice.reels.features.home.data.model.FeedItemDto
import com.practice.reels.features.home.data.model.FeedResponse
import com.practice.reels.features.home.domain.model.Feed
import com.practice.reels.features.home.domain.model.Reel

fun FeedResponse.toFeed(): Feed {
    return data.toFeed()
}

fun FeedDataDto.toFeed(): Feed {
    val reels = feeds.mapNotNull { it.toReel() }
    return Feed(
        items = reels,
        endOfFeed = endOfFeed,
        expiry = expiry,
        pageSession = pageSession
    )
}

fun FeedItemDto.toReel(): Reel? {
    if (video == null) return null
    return Reel(
        id = uuid,
        type = type,
        videoUrl = video.mediaUrl,
        videoUrlM3u8 = video.mediaUrlM3u8,
        thumbnailUrl = video.thumbnailUrl,
        thumbnailUrlL = video.thumbnailUrlL,
        thumbnailUrlS = video.thumbnailUrlS,
        description = video.descriptionText,
        duration = video.duration,
        userName = owner.username,
        userProfilePic = owner.profileImage,
        isAvatar = owner.isAvatar,
        likes = video.noOfSparks,
        commentCount = video.noOfComments,
        shareCount = video.noOfShares,
        viewCount = video.noOfViews,
        groupName = loop?.groupName,
        groupId = loop?.groupId,
        loopSlug = loop?.slug,
        communityId = community?.uuid,
        communityName = community?.name,
        communitySlug = community?.slug,
        communityShareUrl = community?.shareUrl,
        shareUrl = video.shareUrl,
        isSparked = video.isSparked,
        isWatched = video.isWatched
    )
}