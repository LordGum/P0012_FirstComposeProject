package com.example.firstcomposeproject.domain.usecases

import com.example.firstcomposeproject.domain.Repository
import javax.inject.Inject

class CheckAuthStateUseCase  @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke() {
        repository.checkAuthState()
    }
}