package com.example.firstcomposeproject.presentation.main.comments

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstcomposeproject.data.repositories.NewsFeedRepository
import com.example.firstcomposeproject.domain.FeedPost
import kotlinx.coroutines.launch

class CommentViewModel(
    feedPost: FeedPost,
    application: Application
): ViewModel() {
    private val repository = NewsFeedRepository(application)

    private val _screenState = MutableLiveData<CommentScreenState>(CommentScreenState.Initial)
    val screenState: LiveData<CommentScreenState> = _screenState

    init {
        loadComments(feedPost)
    }


    private fun loadComments(feedPost: FeedPost) {
        viewModelScope.launch {
            val comments = repository.getComments(feedPost)
            _screenState.value = CommentScreenState.Comments(
                feedPost = feedPost,
                comments = comments
            )
        }
    }
}