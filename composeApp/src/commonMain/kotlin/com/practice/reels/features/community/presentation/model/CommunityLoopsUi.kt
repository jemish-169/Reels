package com.practice.reels.features.community.presentation.model

data class CommunityLoopsUi(
    val loops: List<LoopUi>,
    val count: Int
)

data class LoopUi(
    val id: String,
    val groupId: String,
    val groupName: String,
    val groupDescription: String?,
    val colorCode: String?,
    val dp: String?,
    val memberCount: String,
    val videoCount: String,
    val viewCount: String,
    val isSubscriber: Boolean,
    val isPostAllowed: Boolean,
    val latestMessageThumbnail: String?,
    val latestMessageOwner: String?,
    val collaborators: List<LoopCollaboratorUi>
)

data class LoopCollaboratorUi(
    val id: String,
    val name: String?,
    val username: String?,
    val profileImage: String?,
    val isAvatar: Boolean
)
