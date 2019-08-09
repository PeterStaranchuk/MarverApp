package com.ps.superheroapp.ui.character_screen

import androidx.paging.PagedList
import com.ps.superheroapp.objects.CharacterLoadEvent
import com.ps.superheroapp.ui.character_screen.list.Character
import com.ps.superheroapp.ui.character_screen.list.CharactersDataSource
import com.ps.superheroapp.ui.character_screen.list.MainThreadExecutor
import io.reactivex.Observable
import java.util.concurrent.Executors
import javax.inject.Inject

class CharactersInteractorImpl @Inject constructor(
    private val dataSource: CharactersDataSource,
    private val config: PagedList.Config
) : CharactersContract.Interactor {

    override fun getCharacters(filter: String?): PagedList<Character> {
        dataSource.filter.searchQuery = nullIfEmpty(filter)
        return PagedList.Builder(dataSource, config)
            .setFetchExecutor(Executors.newFixedThreadPool(3))
            .setNotifyExecutor(MainThreadExecutor())
            .build()
    }

    override fun observeCharactersLoadEvents(): Observable<CharacterLoadEvent> {
        return dataSource.observeCharactersLoadEvents()
    }

    private fun nullIfEmpty(string: String?): String? {
        return if (!string.isNullOrBlank()) string else null
    }
}