package com.practice.reels.features.community.presentation.model

data class CommunityLoopsUi(
    val loops: List<LoopUi>,
    val count: Int
)

data class LoopUi(
    val id: String,
    val groupId: String,
    val groupName: String
)
