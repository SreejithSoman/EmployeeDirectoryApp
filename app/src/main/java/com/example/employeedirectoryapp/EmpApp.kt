package com.example.employeedirectoryapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EmpApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }

}