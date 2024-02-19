package com.example.firstcomposeproject.presentation.main

import android.annotation.SuppressLint
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.firstcomposeproject.navigation.AppNavGraph
import com.example.firstcomposeproject.navigation.Screen
import com.example.firstcomposeproject.navigation.rememberNavigationState
import com.example.firstcomposeproject.presentation.main.comments.CommentsScreen
import com.example.firstcomposeproject.presentation.main.news.NewsFeedScreen
import com.example.firstcomposeproject.presentation.main.news.NewsFeedViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navigationState = rememberNavigationState()

    Scaffold (
        bottomBar = {
            BottomAppBar {

                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favorites,
                    NavigationItem.Person
                )
                items.forEach{item ->
                    NavigationBarItem(
                        selected = (currentRoute == item.screen.route),
                        onClick = { navigationState.navigateTo(item.screen.route)},
                        icon = {
                               Icon(item.icon, contentDescription = null)
                        },
                        label = { Text(text = stringResource(id = item.titleResId))},
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
                            unselectedTextColor = MaterialTheme.colorScheme.onSecondary
                        )
                    )
                }
            }
        }
    ) { paddingValues ->
       AppNavGraph(
           navHostController = navigationState.navHostController,
           newsFeedScreenContent = {
               NewsFeedScreen(
                   paddingValues = paddingValues,
                   onCommentClickListener = {
                       navigationState.navigateToComments(it)
                   }
               )
           },
           commentScreenContent = {feedPost ->
                CommentsScreen(
                    feedPost = feedPost,
                    backPressed = {
                        navigationState.navHostController.popBackStack()
                    }
                )
           },
           favoritesScreenContent = { Text(text = "Favorites")},
           profileScreenContent = { Text(text = "Profile")}
       )
    }
}