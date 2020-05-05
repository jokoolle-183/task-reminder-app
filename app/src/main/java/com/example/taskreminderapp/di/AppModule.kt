package com.example.taskreminderapp.di

import android.content.Context
import androidx.room.Room
import com.example.taskreminderapp.LogRepository
import com.example.taskreminderapp.data.LogDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideLogDatabase(context: Context): LogDatabase {
        return Room.databaseBuilder(
            context,
            LogDatabase::class.java,
            "log_db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideLogRepository(logDb: LogDatabase): LogRepository {
        return LogRepository(logDb)
    }
}