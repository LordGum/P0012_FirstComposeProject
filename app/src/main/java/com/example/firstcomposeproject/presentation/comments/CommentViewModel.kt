package com.example.firstcomposeproject.presentation.comments

import androidx.lifecycle.ViewModel
import com.example.firstcomposeproject.domain.entities.FeedPost
import com.example.firstcomposeproject.domain.usecases.GetCommentUseCase
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CommentViewModel @Inject constructor(
    private val feedPost: FeedPost,
    private val getCommentUseCase: GetCommentUseCase
) : ViewModel() {

    val screenState = getCommentUseCase(feedPost)
        .map {
            CommentScreenState.Comments(
                feedPost = feedPost,
                comments = it
            )
        }
}
