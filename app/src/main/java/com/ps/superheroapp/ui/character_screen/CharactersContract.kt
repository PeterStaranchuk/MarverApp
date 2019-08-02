package com.ps.superheroapp.ui.character_screen

import com.ps.superheroapp.ui.character_screen.list.Character
import io.reactivex.Single

interface CharactersContract {

    interface Interactor {
        fun getCharacters(): Single<Array<Character>>
    }
}