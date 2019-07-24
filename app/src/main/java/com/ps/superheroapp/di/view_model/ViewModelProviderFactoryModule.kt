package com.aximetria.aximetria.di.view_model

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
interface ViewModelProviderFactoryModule {

    @Binds
    fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory
}