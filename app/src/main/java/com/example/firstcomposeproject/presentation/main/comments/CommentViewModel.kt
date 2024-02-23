package com.example.firstcomposeproject.presentation.main.comments

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.firstcomposeproject.data.repositories.NewsFeedRepositoryImpl
import com.example.firstcomposeproject.domain.entities.FeedPost
import com.example.firstcomposeproject.domain.usecases.GetCommentsUseCase
import kotlinx.coroutines.flow.map

class CommentViewModel(
    feedPost: FeedPost,
    application: Application
) : ViewModel() {

    private val repository = NewsFeedRepositoryImpl(application)

    private val getCommentsUseCase = GetCommentsUseCase(repository)

    val screenState = getCommentsUseCase(feedPost)
        .map {
            CommentScreenState.Comments(
                feedPost = feedPost,
                comments = it
            )
        }
}
