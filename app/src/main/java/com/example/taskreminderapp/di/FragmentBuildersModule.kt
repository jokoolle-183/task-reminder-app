package com.example.taskreminderapp.di

import androidx.lifecycle.ViewModel
import com.example.taskreminderapp.ui.add_log_entry.AddLogEntryFragment
import com.example.taskreminderapp.ui.add_log_entry.AddLogEntryViewModel
import com.example.taskreminderapp.ui.log_entries.LogListFragment
import com.example.taskreminderapp.ui.log_entries.LogListViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector(modules = [ViewModelProviderFactoryModule::class])
    abstract fun contributeLogListFragment(): LogListFragment

    @ContributesAndroidInjector(modules = [ViewModelProviderFactoryModule::class])
    abstract fun contributeLogDetailFragment(): AddLogEntryFragment

    @Binds
    @IntoMap
    @ViewModelKey(LogListViewModel::class)
    abstract fun bindLogListViewModel(logViewModel: LogListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddLogEntryViewModel::class)
    abstract fun bindAddLogEntryViewModel(addLogEntryViewModel: AddLogEntryViewModel): ViewModel
}