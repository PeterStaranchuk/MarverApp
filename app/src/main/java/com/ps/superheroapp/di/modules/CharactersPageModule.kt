package com.ps.superheroapp.di.modules

import androidx.paging.PagedList
import com.ps.superheroapp.api.MarvelApiService
import com.ps.superheroapp.objects.SchedulerNames
import com.ps.superheroapp.ui.character_screen.list.Character
import com.ps.superheroapp.ui.character_screen.list.CharactersDataSource
import com.ps.superheroapp.ui.character_screen.list.Filter
import com.ps.superheroapp.ui.character_screen.list.MainThreadExecutor
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.Executors
import javax.inject.Named

@Module
class CharactersPageModule {

    private val pageSize = 10

    @Provides
    fun provideDataSource(
        compositeDisposable: CompositeDisposable,
        superheroApi: MarvelApiService, @Named(SchedulerNames.MAIN) scheduler: Scheduler,
        filter: Filter
    ): CharactersDataSource {
        return CharactersDataSource(compositeDisposable, superheroApi, scheduler, filter)
    }

    @Provides
    fun provideConfig(): PagedList.Config {
        return PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()
    }

    @Provides
    fun providesCharactersPagedList(
        sourceFactory: CharactersDataSource,
        config: PagedList.Config
    ): PagedList<Character> {
        return PagedList.Builder(sourceFactory, config)
            .setFetchExecutor(Executors.newFixedThreadPool(3))
            .setNotifyExecutor(MainThreadExecutor())
            .build()
    }
}