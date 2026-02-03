package com.practice.reels.features.community.data.mapper

import com.practice.reels.features.community.data.model.CommunityDetailsResponse
import com.practice.reels.features.community.data.model.CommunityLoopsResponse
import com.practice.reels.features.community.data.model.CommunityMembersResponse
import com.practice.reels.features.community.data.model.ConversationDto
import com.practice.reels.features.community.data.model.CommunityMemberDto
import com.practice.reels.features.community.domain.model.CommunityDetails
import com.practice.reels.features.community.domain.model.CommunityLoops
import com.practice.reels.features.community.domain.model.CommunityMembers
import com.practice.reels.features.community.domain.model.Loop
import com.practice.reels.features.community.domain.model.Member

fun CommunityDetailsResponse.toDomain(): CommunityDetails {
    val d = data
    return CommunityDetails(
        id = d?.communityId.orEmpty(),
        handle = d?.handle.orEmpty(),
        slug = d?.slug.orEmpty(),
        name = d?.name.orEmpty(),
        description = d?.description,
        colorCode = d?.colorCode,
        textColorCode = d?.textColorCode,
        dp = d?.dp,
        shareUrl = d?.shareUrl,
        memberCount = d?.noOfMembers ?: 0,
        loopCount = d?.noOfLoops ?: 0,
        videoCount = d?.noOfVideos ?: 0,
        viewCount = d?.noOfViews ?: 0,
        leaderName = d?.leader?.name,
        leaderUsername = d?.leader?.nickname,
        leaderProfileImage = d?.leader?.profileImage,
        isLoopCreationAllowed = d?.isLoopCreationAllowed ?: false
    )
}

fun CommunityLoopsResponse.toDomain(): CommunityLoops {
    return CommunityLoops(
        loops = data?.conversations?.mapNotNull { it.toLoop() }.orEmpty(),
        count = data?.noOfData ?: 0
    )
}

private fun ConversationDto.toLoop(): Loop? {
    val g = group ?: return null
    return Loop(
        id = chatId.orEmpty(),
        slug = slug.orEmpty(),
        shareUrl = shareUrl,
        groupId = g.groupId.orEmpty(),
        groupName = g.groupName.orEmpty(),
        groupDescription = g.groupDescription,
        colorCode = g.colorCode,
        textColorCode = g.textColorCode,
        memberCount = g.noOfMembers,
        videoCount = g.noOfVideos,
        isSubscriber = isSubscriber,
        isPostAllowed = isPostAllowed,
        latestMessageThumbnail = latestMessages.firstOrNull()?.thumbnailUrl
    )
}

fun CommunityMembersResponse.toDomain(): CommunityMembers {
    return CommunityMembers(
        members = data?.members?.map { it.toMember() }.orEmpty()
    )
}

private fun CommunityMemberDto.toMember(): Member {
    return Member(
        id = memberId.orEmpty(),
        name = name,
        nickname = nickname,
        bio = bio,
        isAvatar = isAvatar,
        profileImage = profileImage,
        role = role,
        isBrandSystemUser = isBrandSystemUser
    )
}
