package com.example.firstcomposeproject.di

import com.example.firstcomposeproject.domain.entities.FeedPost
import com.example.firstcomposeproject.presentation.ViewModelFactory
import dagger.BindsInstance
import dagger.Subcomponent


@Subcomponent(
    modules = [
        CommentViewModelModule::class
    ]
)
interface CommentScreenComponent {

    fun getViewModelFactory(): ViewModelFactory

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance feedPost: FeedPost
        ): CommentScreenComponent
    }
}