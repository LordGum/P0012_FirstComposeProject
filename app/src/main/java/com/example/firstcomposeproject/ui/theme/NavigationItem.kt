package com.example.firstcomposeproject.ui.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.firstcomposeproject.R


sealed class NavigationItem (
    val titleResId: Int,
    val icon: ImageVector
) {
    object Home: NavigationItem (
        titleResId = R.string.navigation_item_main,
        icon = Icons.Outlined.Home
    )

    object Favorites: NavigationItem (
        titleResId = R.string.navigation_item_favorites,
        icon = Icons.Outlined.Favorite
    )

    object Person: NavigationItem (
        titleResId = R.string.navigation_item_person,
        icon =  Icons.Outlined.Person
    )
}