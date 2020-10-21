package com.example.taskreminderapp.di

import android.app.Application
import com.example.taskreminderapp.BaseApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        AndroidSupportInjectionModule::class,
        FragmentBuildersModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}