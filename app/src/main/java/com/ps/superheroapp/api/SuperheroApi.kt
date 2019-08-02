package com.ps.superheroapp.api

import com.ps.superheroapp.ui.character_screen.list.Character
import io.reactivex.Single
import retrofit2.http.GET

interface SuperheroApi {

    @GET("/v1/public/characters")
    fun searchCharacter(): Single<Array<Character>>

}