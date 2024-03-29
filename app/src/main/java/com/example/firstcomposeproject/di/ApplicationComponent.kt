package com.example.firstcomposeproject.di

import android.content.Context
import com.example.firstcomposeproject.presentation.main.MainActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)

    fun getCommentsScreenComponentFactory(): CommentScreenComponent.Factory

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance context: Context
        ): ApplicationComponent
    }
}