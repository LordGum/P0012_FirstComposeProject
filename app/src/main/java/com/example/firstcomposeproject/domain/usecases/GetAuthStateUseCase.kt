package com.example.firstcomposeproject.domain.usecases

import com.example.firstcomposeproject.domain.Repository
import com.example.firstcomposeproject.domain.entities.AuthState
import kotlinx.coroutines.flow.StateFlow

class GetAuthStateUseCase (
    private val repository: Repository
) {
    operator fun invoke(): StateFlow<AuthState> {
        return repository.getAuthStateFlow()
    }
}