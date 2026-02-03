package com.practice.reels.core.presentation.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.practice.reels.animation.AnimateScreen
import com.practice.reels.core.utils.MessagePasser
import com.practice.reels.features.community.presentation.screen.CommunityDetailScreen
import com.practice.reels.features.home.presentation.screen.HomeScreen

@Composable
fun RootNavGraph(
    messagePasser: MessagePasser
) {
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        messagePasser.messages.collect { message ->
            snackBarHostState.showSnackbar(message)
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
    ) { paddingValues ->
        val rootNavController = rememberNavController()

        NavHost(
            navController = rootNavController,
            startDestination = RootRoute.HomeScreen
        ) {
            composable<RootRoute.HomeScreen>(
                popEnterTransition = AnimateScreen.rightPopEnterTransition(),
                enterTransition = AnimateScreen.leftEnterTransition(),
                popExitTransition = AnimateScreen.rightPopExitTransition(),
                exitTransition = AnimateScreen.leftExitTransition()
            ) {
                HomeScreen(
                    paddingValues = paddingValues,
                    onReelClick = { communityId ->
                        rootNavController.navigate(RootRoute.CommunityDetailScreen(communityId))
                    }
                )
            }

            composable<RootRoute.CommunityDetailScreen>(
                popEnterTransition = AnimateScreen.rightPopEnterTransition(),
                enterTransition = AnimateScreen.leftEnterTransition(),
                popExitTransition = AnimateScreen.rightPopExitTransition(),
                exitTransition = AnimateScreen.leftExitTransition()
            ) { backStackEntry ->
                val route = backStackEntry.toRoute<RootRoute.CommunityDetailScreen>()
                CommunityDetailScreen(
                    communityId = route.communityId,
                    paddingValues = paddingValues,
                    onBackClick = {
                        rootNavController.navigateUp()
                    }
                )
            }
        }
    }
}