package com.ps.superheroapp.ui.character_screen

import com.ps.superheroapp.api.SuperheroApi
import com.ps.superheroapp.ui.character_screen.list.Character
import io.reactivex.Single
import javax.inject.Inject

class CharactersInteractorImpl @Inject constructor(private val superheroApi: SuperheroApi) :
    CharactersContract.Interactor {

    override fun getCharacters(): Single<Array<Character>> {
        return superheroApi.searchCharacter()
    }
}