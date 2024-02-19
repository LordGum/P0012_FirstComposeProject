package com.example.firstcomposeproject.presentation.main.comments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firstcomposeproject.domain.Comment
import com.example.firstcomposeproject.domain.FeedPost

class CommentViewModel(
    feedPost: FeedPost
): ViewModel() {

    private val _screenState = MutableLiveData<CommentScreenState>(CommentScreenState.Initial)
    val screenState: LiveData<CommentScreenState> = _screenState

    init {
        loadComments(feedPost)
    }


    private fun loadComments(feedPost: FeedPost) {
        val comments = mutableListOf<Comment>().apply {
            repeat(10) {
                add(Comment(id = it))
            }
        }
        _screenState.value = CommentScreenState.Comments (
            feedPost = feedPost,
            comments = comments
        )
    }
}