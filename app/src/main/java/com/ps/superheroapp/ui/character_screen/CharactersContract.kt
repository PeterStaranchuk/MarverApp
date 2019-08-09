package com.ps.superheroapp.ui.character_screen

import androidx.paging.PagedList
import com.ps.superheroapp.objects.CharacterLoadEvent
import com.ps.superheroapp.ui.character_screen.list.Character
import io.reactivex.Observable

interface CharactersContract {

    @javax.inject.Scope
    @Retention(AnnotationRetention.RUNTIME)
    annotation class Scope

    interface Interactor {
        fun getCharacters(filter: String? = null): PagedList<Character>
        fun observeCharactersLoadEvents(): Observable<CharacterLoadEvent>
    }
}