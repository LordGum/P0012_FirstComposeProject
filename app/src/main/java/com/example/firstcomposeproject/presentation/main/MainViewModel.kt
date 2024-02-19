package com.example.firstcomposeproject.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firstcomposeproject.domain.FeedPost
import com.example.firstcomposeproject.domain.StatisticType
import java.lang.IllegalStateException

class MainViewModel: ViewModel() {
    private val sourceList = mutableListOf<FeedPost>().apply {
        repeat(10) {
            add(FeedPost(id = it))
        }
    }

    private val _feedPosts = MutableLiveData<List<FeedPost>> (sourceList)
    val feedPosts: LiveData<List<FeedPost>> = _feedPosts

    fun updateCount(feedPost: FeedPost, type: StatisticType) {
        val oldPosts = feedPosts.value?.toMutableList() ?: mutableListOf()
        val oldStatistics = feedPost.statistics
        val newStatistics = oldStatistics.toMutableMap().apply {
            val oldItemCount = this[type] ?: throw IllegalStateException()
            replace(type, oldItemCount + 1)
        }

        val newFeedPost = feedPost.copy(statistics = newStatistics)
        _feedPosts.value = oldPosts.apply {
            replaceAll{
                if (it.id == newFeedPost.id) {newFeedPost}
                else it
            }
        }
    }

    fun delete(feedPost: FeedPost) {
        val oldPosts = feedPosts.value?.toMutableList() ?: mutableListOf()
        oldPosts.remove(feedPost)
        _feedPosts.value = oldPosts
    }
}