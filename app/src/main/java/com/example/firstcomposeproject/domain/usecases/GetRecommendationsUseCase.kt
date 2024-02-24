package com.example.firstcomposeproject.domain.usecases

import com.example.firstcomposeproject.domain.Repository
import com.example.firstcomposeproject.domain.entities.FeedPost
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetRecommendationsUseCase  @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(): StateFlow<List<FeedPost>> {
        return repository.getRecommendations()
    }
}