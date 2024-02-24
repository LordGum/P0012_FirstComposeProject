package com.example.firstcomposeproject.domain.usecases

import com.example.firstcomposeproject.domain.Repository
import com.example.firstcomposeproject.domain.entities.AuthState
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetAuthStateUseCase  @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(): StateFlow<AuthState> {
        return repository.getAuthStateFlow()
    }
}