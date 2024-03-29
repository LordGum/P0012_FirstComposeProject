package com.example.firstcomposeproject.presentation.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.firstcomposeproject.navigation.AppNavGraph
import com.example.firstcomposeproject.navigation.rememberNavigationState
import com.example.firstcomposeproject.presentation.ViewModelFactory
import com.example.firstcomposeproject.presentation.comments.CommentScreen
import com.example.firstcomposeproject.presentation.news.NewsFeedScreen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(viewModelFactory: ViewModelFactory) {
    val navigationState = rememberNavigationState()

    Scaffold (
        containerColor = MaterialTheme.colorScheme.onSecondary,
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
                   viewModelFactory = viewModelFactory,
                   paddingValues = paddingValues,
                   onCommentClickListener = {
                       navigationState.navigateToComments(it)
                   }
               )
           },
           commentScreenContent = {feedPost ->
               Log.d("MATAG", "feedPost = $feedPost")
                CommentScreen(
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