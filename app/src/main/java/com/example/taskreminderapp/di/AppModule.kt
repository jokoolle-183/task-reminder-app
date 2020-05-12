package com.example.taskreminderapp.di

import android.app.Application
import androidx.room.Room
import com.example.taskreminderapp.LogRepository
import com.example.taskreminderapp.data.LogDao
import com.example.taskreminderapp.data.LogDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideLogDatabase(application: Application): LogDatabase {
        return Room.databaseBuilder(
            application,
            LogDatabase::class.java,
            "log_db"
        ).build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideLogDao(logDb: LogDatabase): LogDao {
        return logDb.getLogDao()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideLogRepository(logDb: LogDatabase): LogRepository {
        return LogRepository(logDb)
    }
}