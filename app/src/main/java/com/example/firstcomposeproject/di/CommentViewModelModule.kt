package com.example.firstcomposeproject.di

import androidx.lifecycle.ViewModel
import com.example.firstcomposeproject.presentation.comments.CommentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface CommentViewModelModule {

    @IntoMap
    @ViewModelKey(CommentViewModel::class)
    @Binds
    fun bindCommentViewModel(viewModel: CommentViewModel): ViewModel
}