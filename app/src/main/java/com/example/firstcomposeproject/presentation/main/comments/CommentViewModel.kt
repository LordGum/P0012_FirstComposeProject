package com.example.firstcomposeproject.presentation.main.comments

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.firstcomposeproject.data.repositories.NewsFeedRepository
import com.example.firstcomposeproject.domain.FeedPost
import kotlinx.coroutines.flow.map

class CommentViewModel(
    feedPost: FeedPost,
    application: Application
) : ViewModel() {

    private val repository = NewsFeedRepository(application)

    val screenState = repository.getComments(feedPost)
        .map {
            CommentScreenState.Comments(
                feedPost = feedPost,
                comments = it
            )
        }
}
