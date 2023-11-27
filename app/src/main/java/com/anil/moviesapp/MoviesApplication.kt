package com.anil.moviesapp

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MoviesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext

    }

    companion object {

        lateinit var appContext: Context

    }
}