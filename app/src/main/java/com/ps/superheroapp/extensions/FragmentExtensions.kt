package com.ps.superheroapp.extensions

import androidx.fragment.app.Fragment
import com.ps.superheroapp.SuperHeroApplication
import com.ps.superheroapp.di.AppComponent

fun Fragment.getApp(): SuperHeroApplication {
    return this.context?.applicationContext as SuperHeroApplication
}

fun Fragment.getAppComponent(): AppComponent {
    return getApp().component
}