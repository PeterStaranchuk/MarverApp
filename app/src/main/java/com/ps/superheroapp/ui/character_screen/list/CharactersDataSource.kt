package com.ps.superheroapp.ui.character_screen.list

import androidx.paging.PositionalDataSource
import com.ps.superheroapp.api.MarvelApiService
import com.ps.superheroapp.objects.CharacterLoadEvent
import com.ps.superheroapp.objects.SchedulerNames
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import javax.inject.Named

class CharactersDataSource(
    private val compositeDisposable: CompositeDisposable,
    private val marvelApi: MarvelApiService,
    @Named(SchedulerNames.MAIN) private val scheduler: Scheduler,
    val filter: Filter
) : PositionalDataSource<Character>() {

    private val onEvent = PublishSubject.create<CharacterLoadEvent>()

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Character>) {
        compositeDisposable.add(marvelApi.searchCharacter(params.pageSize, 0, filter.searchQuery)
            .observeOn(scheduler)
            .doOnSubscribe {
                onEvent.onNext(CharacterLoadEvent.INITIAL_LOAD_STARTED)
            }
            .subscribe({
                callback.onResult(it.data.results ?: arrayListOf(), 0)
                onEvent.onNext(CharacterLoadEvent.LOADED)
            }, {
                onEvent.onNext(CharacterLoadEvent.ERROR)
            })
        )
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Character>) {
        compositeDisposable.add(
            marvelApi.searchCharacter(params.loadSize, params.startPosition, filter.searchQuery)
                .observeOn(scheduler)
                .doOnSubscribe {
                    onEvent.onNext(CharacterLoadEvent.NEXT_PAGE_LOAD_STARTED)
                }
                .subscribe({
                    callback.onResult(it.data.results ?: arrayListOf())
                    onEvent.onNext(CharacterLoadEvent.LOADED)
                }, {
                    onEvent.onNext(CharacterLoadEvent.ERROR)
                })
        )
    }

    fun observeCharactersLoadEvents(): Observable<CharacterLoadEvent> = onEvent
}