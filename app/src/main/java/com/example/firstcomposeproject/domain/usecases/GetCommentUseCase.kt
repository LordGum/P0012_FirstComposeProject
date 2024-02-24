package com.example.firstcomposeproject.domain.usecases

import com.example.firstcomposeproject.domain.Repository
import com.example.firstcomposeproject.domain.entities.FeedPost
import com.example.firstcomposeproject.domain.entities.PostComment
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCommentUseCase  @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(feedPost: FeedPost): Flow<List<PostComment>> {
        return repository.getComments(feedPost)
    }
}