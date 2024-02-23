package com.example.firstcomposeproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.firstcomposeproject.domain.entities.FeedPost

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    newsFeedScreenContent: @Composable () -> Unit,
    commentScreenContent: @Composable (FeedPost) -> Unit,
    favoritesScreenContent: @Composable () -> Unit,
    profileScreenContent: @Composable () -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route
    ) {

        homeScreenGraph(
            newsFeedScreenContent = newsFeedScreenContent,
            commentsScreenContent = commentScreenContent
        )
        composable(Screen.Favorites.route) {
            favoritesScreenContent()
        }
        composable(Screen.Profile.route) {
            profileScreenContent()
        }
    }
}