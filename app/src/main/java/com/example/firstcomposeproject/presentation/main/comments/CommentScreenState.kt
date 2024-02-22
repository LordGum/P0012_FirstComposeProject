package com.example.firstcomposeproject.presentation.main.comments

import com.example.firstcomposeproject.domain.PostComment
import com.example.firstcomposeproject.domain.FeedPost

sealed class CommentScreenState{
    object Initial : CommentScreenState()

    data class Comments(
        val feedPost: FeedPost,
        val comments: List<PostComment>
    ): CommentScreenState()

}
