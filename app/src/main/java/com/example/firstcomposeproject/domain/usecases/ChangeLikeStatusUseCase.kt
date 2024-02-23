package com.example.firstcomposeproject.domain.usecases

import com.example.firstcomposeproject.domain.Repository
import com.example.firstcomposeproject.domain.entities.FeedPost

class ChangeLikeStatusUseCase (
    private val repository: Repository
) {
    suspend operator fun invoke(feedPost: FeedPost) =  repository.changeLikeStatus(feedPost)
}