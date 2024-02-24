package com.example.firstcomposeproject.presentation.comments

import com.example.firstcomposeproject.domain.entities.FeedPost
import com.example.firstcomposeproject.domain.entities.PostComment

sealed class CommentScreenState{
    object Initial : CommentScreenState()

    data class Comments(
        val feedPost: FeedPost,
        val comments: List<PostComment>
    ): CommentScreenState()

}
