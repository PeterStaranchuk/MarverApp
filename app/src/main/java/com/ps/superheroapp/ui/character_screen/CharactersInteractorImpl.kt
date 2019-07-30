package com.ps.superheroapp.ui.character_screen

import com.ps.superheroapp.ui.character_screen.list.Character
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CharactersInteractorImpl @Inject constructor() : CharactersContract.Interactor {

    override fun getCharacters(): Observable<Array<Character>> {
        return Observable.just(arrayOf<Character>()) //TODO implement
            .subscribeOn(Schedulers.io())
    }
}