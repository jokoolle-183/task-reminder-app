package com.example.taskreminderapp.di

import androidx.lifecycle.ViewModel
import com.example.taskreminderapp.LogViewModel
import com.example.taskreminderapp.ui.LogListFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector(modules = [ViewModelProviderFactoryModule::class])
    abstract fun contributeLogListFragment(): LogListFragment

    @Binds
    @IntoMap
    @ViewModelKey(LogViewModel::class)
    abstract fun bindViewModel(logViewModel: LogViewModel): ViewModel

//    @ContributesAndroidInjector(modules = [ViewModelModule::class, ViewModelProviderFactoryModule::class])
//    abstract fun contributeLogDetailFragment(): LogDetailFragment
}