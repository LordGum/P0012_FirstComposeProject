package com.example.firstcomposeproject.presentation.main.news

import com.example.firstcomposeproject.domain.FeedPost

sealed class NewsFeedScreenState {
    object Initial: NewsFeedScreenState()

    data class Posts(val posts: List<FeedPost>) : NewsFeedScreenState()
}