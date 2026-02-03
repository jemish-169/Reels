package com.practice.reels.features.community.data.mapper

import com.practice.reels.features.community.data.model.CommunityDetailsResponse
import com.practice.reels.features.community.data.model.CommunityLeaderDto
import com.practice.reels.features.community.data.model.CommunityLoopsResponse
import com.practice.reels.features.community.data.model.CommunityMembersResponse
import com.practice.reels.features.community.data.model.ConversationDto
import com.practice.reels.features.community.data.model.CommunityMemberDto
import com.practice.reels.features.community.data.model.GroupMemberDto
import com.practice.reels.features.community.data.model.ModeratorDto
import com.practice.reels.features.community.data.model.SocialLinksDto
import com.practice.reels.features.community.domain.model.CommunityDetails
import com.practice.reels.features.community.domain.model.CommunityLeader
import com.practice.reels.features.community.domain.model.CommunityLoops
import com.practice.reels.features.community.domain.model.CommunityMembers
import com.practice.reels.features.community.domain.model.Loop
import com.practice.reels.features.community.domain.model.LoopCollaborator
import com.practice.reels.features.community.domain.model.Member
import com.practice.reels.features.community.domain.model.Moderator
import com.practice.reels.features.community.domain.model.SocialLinks

fun CommunityDetailsResponse.toDomain(): CommunityDetails {
    val d = data
    return CommunityDetails(
        id = d?.communityId.orEmpty(),
        handle = d?.handle.orEmpty(),
        slug = d?.slug.orEmpty(),
        name = d?.name.orEmpty(),
        type = d?.type ?: 0,
        description = d?.description,
        colorCode = d?.colorCode,
        textColorCode = d?.textColorCode,
        banner = d?.banner,
        dp = d?.dp,
        shareUrl = d?.shareUrl,
        memberCount = d?.noOfMembers ?: 0,
        loopCount = d?.noOfLoops ?: 0,
        videoCount = d?.noOfVideos ?: 0,
        viewCount = d?.noOfViews ?: 0,
        commentCount = d?.noOfComments ?: 0,
        reactionCount = d?.noOfReactions ?: 0,
        shareCount = d?.noOfShares ?: 0,
        createdAt = d?.createdAt,
        isLoopCreationAllowed = d?.isLoopCreationAllowed ?: false,
        isCommunityJoinRequested = d?.isCommunityJoinRequested ?: false,
        leader = d?.leader?.toLeader(),
        moderators = d?.moderators?.map { it.toModerator() }.orEmpty(),
        socialLinks = d?.socialLinks?.toSocialLinks()
    )
}

private fun CommunityLeaderDto.toLeader(): CommunityLeader {
    return CommunityLeader(
        id = memberId.orEmpty(),
        nickname = nickname,
        name = name,
        bio = bio,
        profileImage = profileImage,
        isAvatar = isAvatar,
        role = role,
        isBrandSystemUser = isBrandSystemUser
    )
}

private fun ModeratorDto.toModerator(): Moderator {
    return Moderator(
        id = memberId.orEmpty(),
        nickname = nickname,
        name = name,
        bio = bio,
        profileImage = profileImage,
        isAvatar = isAvatar,
        role = role,
        isBrandSystemUser = isBrandSystemUser
    )
}

private fun SocialLinksDto.toSocialLinks(): SocialLinks {
    return SocialLinks(
        webUrl = socialWebUrl,
        twitterUrl = twitter?.url,
        linkedinUrl = linkedin?.url,
        instagramUrl = insta?.url,
        discordUrl = discordUrl,
        redditUrl = redditId?.url
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
        type = type ?: 0,
        position = position ?: 0,
        groupId = g.groupId.orEmpty(),
        groupName = g.groupName.orEmpty(),
        groupDescription = g.groupDescription,
        colorCode = g.colorCode,
        textColorCode = g.textColorCode,
        dp = g.dp,
        memberCount = g.noOfMembers,
        subscriberCount = g.noOfSubscribers,
        videoCount = g.noOfVideos,
        viewCount = g.noOfViews,
        isSubscriber = isSubscriber,
        isPostAllowed = isPostAllowed,
        isViewAllowed = isViewAllowed,
        isWelcomeLoop = isWelcomeLoop,
        unreadMessageCount = unreadMessageCount,
        latestMessageAt = latestMessageAt,
        latestMessageThumbnail = latestMessages.firstOrNull()?.thumbnailUrl,
        latestMessageOwner = latestMessages.firstOrNull()?.owner?.username,
        collaborators = g.members.take(3).map { it.toCollaborator() }
    )
}

private fun GroupMemberDto.toCollaborator(): LoopCollaborator {
    return LoopCollaborator(
        id = memberId.orEmpty(),
        name = name,
        username = username,
        profileImage = profileImage,
        isAvatar = isAvatar
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
        status = status,
        isBrandSystemUser = isBrandSystemUser
    )
}
