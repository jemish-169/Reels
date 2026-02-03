package com.practice.reels.features.community.domain.model

data class CommunityDetails(
    val id: String,
    val handle: String,
    val slug: String,
    val name: String,
    val type: Int,
    val description: String?,
    val colorCode: String?,
    val textColorCode: String?,
    val banner: String?,
    val dp: String?,
    val shareUrl: String?,
    val memberCount: Int,
    val loopCount: Int,
    val videoCount: Int,
    val viewCount: Int,
    val commentCount: Int,
    val reactionCount: Int,
    val shareCount: Int,
    val createdAt: String?,
    val isLoopCreationAllowed: Boolean,
    val isCommunityJoinRequested: Boolean,
    val leader: CommunityLeader?,
    val moderators: List<Moderator>,
    val socialLinks: SocialLinks?
)

data class CommunityLeader(
    val id: String,
    val nickname: String?,
    val name: String?,
    val bio: String?,
    val profileImage: String?,
    val isAvatar: Boolean,
    val role: Int?,
    val isBrandSystemUser: Boolean
)

data class Moderator(
    val id: String,
    val nickname: String?,
    val name: String?,
    val bio: String?,
    val profileImage: String?,
    val isAvatar: Boolean,
    val role: Int?,
    val isBrandSystemUser: Boolean
)

data class SocialLinks(
    val webUrl: String?,
    val twitterUrl: String?,
    val linkedinUrl: String?,
    val instagramUrl: String?,
    val discordUrl: String?,
    val redditUrl: String?
)

data class CommunityLoops(
    val loops: List<Loop>,
    val count: Int
)

data class Loop(
    val id: String,
    val slug: String,
    val shareUrl: String?,
    val type: Int,
    val position: Int,
    val groupId: String,
    val groupName: String,
    val groupDescription: String?,
    val colorCode: String?,
    val textColorCode: String?,
    val dp: String?,
    val memberCount: Int,
    val subscriberCount: Int,
    val videoCount: Int,
    val viewCount: Int,
    val isSubscriber: Boolean,
    val isPostAllowed: Boolean,
    val isViewAllowed: Boolean,
    val isWelcomeLoop: Boolean,
    val unreadMessageCount: Int,
    val latestMessageAt: String?,
    val latestMessageThumbnail: String?,
    val latestMessageThumbnails: List<String>,
    val latestMessageOwner: String?,
    val collaborators: List<LoopCollaborator>
)

data class LoopCollaborator(
    val id: String,
    val name: String?,
    val username: String?,
    val profileImage: String?,
    val isAvatar: Boolean
)

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
    val status: Int?,
    val isBrandSystemUser: Boolean
)
