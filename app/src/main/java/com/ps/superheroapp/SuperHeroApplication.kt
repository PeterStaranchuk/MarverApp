package com.ps.superheroapp

import android.app.Application
import com.ps.superheroapp.di.AppComponent
import com.ps.superheroapp.di.DaggerAppComponent

class SuperHeroApplication : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
            .withApplication(this)
            .build()
    }
}