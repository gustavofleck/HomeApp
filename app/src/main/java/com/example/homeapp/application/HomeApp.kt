package com.example.homeapp.application

import android.app.Application
import com.example.homeapp.notes.di.annotationModule
import com.example.homeapp.login.di.loginModule
import com.google.firebase.Firebase
import com.google.firebase.initialize
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class HomeApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Firebase.initialize(this)
        startKoin {
            androidLogger()
            androidContext(this@HomeApp)
            modules(loginModule + annotationModule)
        }
    }

}