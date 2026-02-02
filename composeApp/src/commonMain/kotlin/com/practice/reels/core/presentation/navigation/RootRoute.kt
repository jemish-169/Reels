package com.practice.reels.core.presentation.navigation

import kotlinx.serialization.Serializable

sealed class RootRoute {

    // All the Main modules and Screen that do not apply bottom nav bar, will be defined here

    @Serializable
    data object HomeScreen : RootRoute()
}