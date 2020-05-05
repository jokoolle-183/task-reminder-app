package com.example.taskreminderapp.di

import com.example.taskreminderapp.ui.LogDetailFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(logDetailFragment: LogDetailFragment)

    @Component.Builder
    interface Builder {

    }
}