package com.ps.superheroapp.di.modules

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

@Module
class SchedulersModule {

    @Provides
    fun provideMainScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}