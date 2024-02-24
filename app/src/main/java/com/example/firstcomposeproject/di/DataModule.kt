package com.example.firstcomposeproject.di

import android.content.Context
import com.example.firstcomposeproject.data.network.ApiFactory
import com.example.firstcomposeproject.data.network.ApiService
import com.example.firstcomposeproject.data.repositories.NewsFeedRepositoryImpl
import com.example.firstcomposeproject.domain.Repository
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindRepository(impl: NewsFeedRepositoryImpl): Repository

    companion object {

        @ApplicationScope
        @Provides
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }

        @ApplicationScope
        @Provides
        fun provideVkStorage(
            context: Context
        ): VKPreferencesKeyValueStorage {
            return VKPreferencesKeyValueStorage(context)
        }
    }
}
