package com.example.taskreminderapp

import android.app.Application
import com.example.taskreminderapp.di.AppComponent

class BaseApp : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

    }
}