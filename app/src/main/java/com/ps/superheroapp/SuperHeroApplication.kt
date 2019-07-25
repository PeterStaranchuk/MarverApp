package com.ps.superheroapp

import android.app.Application
import com.ps.superheroapp.di.AppComponent
import com.ps.superheroapp.di.DaggerAppComponent
import com.squareup.picasso.Picasso
import javax.inject.Inject

class SuperHeroApplication : Application() {

    @Inject
    lateinit var picasso: Picasso

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
                .withApplication(this)
                .build()

        component.inject(this)
    }
}