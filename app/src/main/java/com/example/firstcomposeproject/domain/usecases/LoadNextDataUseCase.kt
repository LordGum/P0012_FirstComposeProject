package com.example.firstcomposeproject.domain.usecases

import com.example.firstcomposeproject.domain.Repository

class LoadNextDataUseCase (
    private val repository: Repository
) {
    suspend operator fun invoke() {
        repository.loadNextData()
    }
}