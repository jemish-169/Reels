package com.practice.reels.core.presentation.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.practice.reels.animation.AnimateScreen
import com.practice.reels.core.utils.MessagePasser
import com.practice.reels.features.home.presentation.screen.HomeScreen

@Composable
fun RootNavGraph(
    onFinish: () -> Unit,
    messagePasser: MessagePasser
) {
    // to show app content in safe area and leave space for status bar and navigation buttons
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
                    paddingValues = paddingValues
                )
            }
        }
    }
}

private fun handleBackClick(rootNavController: NavHostController, onBackOrFinish: () -> Unit) {
    if (rootNavController.previousBackStackEntry == null) onBackOrFinish()
    else rootNavController.navigateUp()
}