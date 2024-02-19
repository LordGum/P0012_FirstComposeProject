package com.example.firstcomposeproject.navigation

sealed class Screen(
    val route: String
) {
    object NewsFeed: Screen(ROUTE_NEWS_FEED)
    object Favorites: Screen(ROUTE_FAVORITES)
    object Profile: Screen(ROUTE_PROFILE)
    
    private companion object {
        const val ROUTE_NEWS_FEED = "news_feed"
        const val ROUTE_FAVORITES = "favorites"
        const val ROUTE_PROFILE = "profile"
    }
}