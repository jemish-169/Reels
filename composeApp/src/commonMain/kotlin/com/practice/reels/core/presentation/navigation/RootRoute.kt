package com.practice.reels.core.presentation.navigation

import kotlinx.serialization.Serializable

sealed class RootRoute {

    @Serializable
    data object HomeScreen : RootRoute()

    @Serializable
    data class CommunityDetailScreen(val communityId: String) : RootRoute()
}