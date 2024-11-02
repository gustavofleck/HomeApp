package com.example.homeapp.application

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.initialize

class HomeApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Firebase.initialize(this)
    }

}