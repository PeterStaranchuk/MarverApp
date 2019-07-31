package com.ps.superheroapp.di.modules

import com.ps.superheroapp.objects.SchedulerNames
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named

@Module
class SchedulersModule {

    @Named(SchedulerNames.MAIN)
    @Provides
    fun provideMainScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    @Named(SchedulerNames.IO)
    @Provides
    fun provideIoScheduler(): Scheduler {
        return Schedulers.io()
    }
}