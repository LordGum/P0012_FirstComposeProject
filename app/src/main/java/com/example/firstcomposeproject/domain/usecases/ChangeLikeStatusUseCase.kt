package com.example.firstcomposeproject.domain.usecases

import com.example.firstcomposeproject.domain.Repository
import com.example.firstcomposeproject.domain.entities.FeedPost
import javax.inject.Inject

class ChangeLikeStatusUseCase  @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(feedPost: FeedPost) =  repository.changeLikeStatus(feedPost)
}