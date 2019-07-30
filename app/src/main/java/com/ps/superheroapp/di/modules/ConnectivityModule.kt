package com.ps.superheroapp.di.modules

import android.content.Context
import com.ps.superheroapp.objects.ConnectivityChecker
import com.ps.superheroapp.objects.ConnectivityCheckerImpl
import dagger.Module
import dagger.Provides

@Module
class ConnectivityModule {

    @Provides
    fun connectivityChecker(context: Context): ConnectivityChecker {
        return ConnectivityCheckerImpl(context)
    }
}