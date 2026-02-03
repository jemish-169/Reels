package com.practice.reels.features.community.presentation.mapper

import com.practice.reels.core.presentation.utils.formatCount
import com.practice.reels.core.presentation.utils.formatRelativeTime
import com.practice.reels.features.community.domain.model.CommunityDetails
import com.practice.reels.features.community.domain.model.CommunityLoops
import com.practice.reels.features.community.domain.model.CommunityMembers
import com.practice.reels.features.community.domain.model.Loop
import com.practice.reels.features.community.domain.model.LoopCollaborator
import com.practice.reels.features.community.domain.model.Member
import com.practice.reels.features.community.presentation.model.CommunityDetailsUi
import com.practice.reels.features.community.presentation.model.CommunityLoopsUi
import com.practice.reels.features.community.presentation.model.CommunityMembersUi
import com.practice.reels.features.community.presentation.model.LoopCollaboratorUi
import com.practice.reels.features.community.presentation.model.LoopUi
import com.practice.reels.features.community.presentation.model.MemberUi

fun CommunityDetails.toUi(): CommunityDetailsUi {
    return CommunityDetailsUi(
        id = this.id,
        name = this.name,
        handle = this.handle,
        slug = this.slug,
        description = this.description,
        colorCode = this.colorCode,
        textColorCode = this.textColorCode,
        banner = this.banner,
        dp = this.dp,
        shareUrl = this.shareUrl,
        memberCount = formatCount(this.memberCount),
        loopCount = formatCount(this.loopCount),
        videoCount = formatCount(this.videoCount),
        viewCount = formatCount(this.viewCount),
        isLoopCreationAllowed = this.isLoopCreationAllowed,
        isCommunityJoinRequested = this.isCommunityJoinRequested,
        leaderName = this.leader?.name,
        leaderUsername = this.leader?.nickname,
        leaderProfileImage = this.leader?.profileImage,
        leaderIsAvatar = this.leader?.isAvatar ?: false
    )
}

fun CommunityLoops.toUi(): CommunityLoopsUi {
    return CommunityLoopsUi(
        loops = this.loops.map { it.toUi() },
        count = this.count
    )
}

private fun Loop.toUi(): LoopUi {
    return LoopUi(
        id = this.id,
        groupId = this.groupId,
        groupName = this.groupName,
        groupDescription = this.groupDescription,
        colorCode = this.colorCode,
        dp = this.dp,
        memberCount = formatCount(this.memberCount),
        videoCount = formatCount(this.videoCount),
        viewCount = formatCount(this.viewCount),
        isSubscriber = this.isSubscriber,
        isPostAllowed = this.isPostAllowed,
        latestMessageAt = this.latestMessageAt?.toLongOrNull()?.let { formatRelativeTime(it) }
            ?: this.latestMessageAt,
        latestMessageThumbnail = this.latestMessageThumbnail,
        latestMessageThumbnails = this.latestMessageThumbnails,
        latestMessageOwner = this.latestMessageOwner,
        collaborators = this.collaborators.map { it.toUi() }
    )
}

private fun LoopCollaborator.toUi(): LoopCollaboratorUi {
    return LoopCollaboratorUi(
        id = this.id,
        name = this.name,
        username = this.username,
        profileImage = this.profileImage,
        isAvatar = this.isAvatar
    )
}

fun CommunityMembers.toUi(): CommunityMembersUi {
    return CommunityMembersUi(
        members = this.members.map { it.toUi() }
    )
}

private fun Member.toUi(): MemberUi {
    return MemberUi(
        id = this.id,
        nickname = this.nickname,
        name = this.name,
        bio = this.bio,
        profileImage = this.profileImage,
        isAvatar = this.isAvatar,
        role = this.role,
        isBrandSystemUser = this.isBrandSystemUser
    )
}
