package com.ps.superheroapp.ui.character_screen

import com.ps.superheroapp.ui.character_screen.list.Character
import io.reactivex.Observable

interface CharactersContract {

    interface Interactor {
        fun getCharacters(): Observable<Array<Character>>
    }
}