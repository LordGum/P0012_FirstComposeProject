package com.example.firstcomposeproject.presentation

import android.app.Application
import com.example.firstcomposeproject.di.ApplicationComponent
import com.example.firstcomposeproject.di.DaggerApplicationComponent

class NewsFeedApplication : Application() {
    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}