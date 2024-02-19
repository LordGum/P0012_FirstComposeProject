package com.example.firstcomposeproject.presentation.main.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firstcomposeproject.domain.FeedPost
import com.example.firstcomposeproject.domain.StatisticType
import java.lang.IllegalStateException

class NewsFeedViewModel: ViewModel() {
    private val sourceList = mutableListOf<FeedPost>().apply {
        repeat(10) {
            add(FeedPost(id = it))
        }
    }

    private val initialState = NewsFeedScreenState.Posts(posts = sourceList)

    private val _screenState = MutableLiveData<NewsFeedScreenState>(initialState)
    val screenState: LiveData<NewsFeedScreenState> = _screenState

    fun updateCount(feedPost: FeedPost, type: StatisticType) {
        val currentState = screenState.value
        if (currentState !is NewsFeedScreenState.Posts) return

        val oldPosts = currentState.posts.toMutableList()
        val oldStatistics = feedPost.statistics
        val newStatistics = oldStatistics.toMutableMap().apply {
            val oldItemCount = this[type] ?: throw IllegalStateException()
            replace(type, oldItemCount + 1)
        }

        val newFeedPost = feedPost.copy(statistics = newStatistics)
        val newPosts = oldPosts.apply {
            replaceAll{
                if (it.id == newFeedPost.id) {newFeedPost}
                else it
            }
        }
        _screenState.value = NewsFeedScreenState.Posts(posts = newPosts)
    }

    fun delete(feedPost: FeedPost) {
        val currentState = screenState.value
        if (currentState !is NewsFeedScreenState.Posts) return

        val oldPosts = currentState.posts.toMutableList()
        oldPosts.remove(feedPost)
        _screenState.value = NewsFeedScreenState.Posts(oldPosts)
    }
}