package com.example.firstcomposeproject.presentation.main.news

import com.example.firstcomposeproject.domain.entities.FeedPost

sealed class NewsFeedScreenState {
    object Initial: NewsFeedScreenState()

    object Loading : NewsFeedScreenState()

    data class Posts(
        val posts: List<FeedPost>,
        val nextDataIsLoading: Boolean = false
    ) : NewsFeedScreenState()
}