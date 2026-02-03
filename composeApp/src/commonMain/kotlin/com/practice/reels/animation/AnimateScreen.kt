package com.practice.reels.animation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween

object AnimateScreen {
    fun leftEnterTransition(): AnimatedContentTransitionScope<*>.() -> EnterTransition? = {
        slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Left,
            animationSpec = tween(300)
        )
    }

    fun rightPopEnterTransition(): AnimatedContentTransitionScope<*>.() -> EnterTransition? = {
        slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Right,
            animationSpec = tween(300)
        )
    }

    fun leftExitTransition(): AnimatedContentTransitionScope<*>.() -> ExitTransition? = {
        slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.Left,
            animationSpec = tween(300)
        )
    }

    fun rightPopExitTransition(): AnimatedContentTransitionScope<*>.() -> ExitTransition? = {
        slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.Right,
            animationSpec = tween(300)
        )
    }

}