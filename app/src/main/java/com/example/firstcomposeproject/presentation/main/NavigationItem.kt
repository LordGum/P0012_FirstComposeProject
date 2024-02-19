package com.example.firstcomposeproject.presentation.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.firstcomposeproject.R
import com.example.firstcomposeproject.navigation.Screen


sealed class NavigationItem (
    val screen: Screen,
    val titleResId: Int,
    val icon: ImageVector
) {
    object Home: NavigationItem(
        screen = Screen.NewsFeed,
        titleResId = R.string.navigation_item_main,
        icon = Icons.Outlined.Home
    )

    object Favorites: NavigationItem(
        screen = Screen.Favorites,
        titleResId = R.string.navigation_item_favorites,
        icon = Icons.Outlined.Favorite
    )

    object Person: NavigationItem(
        screen = Screen.Profile,
        titleResId = R.string.navigation_item_person,
        icon =  Icons.Outlined.Person
    )
}