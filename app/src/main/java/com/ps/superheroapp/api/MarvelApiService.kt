package com.ps.superheroapp.api

import com.ps.superheroapp.ui.character_screen.list.CharactersResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApiService {

    @GET("/v1/public/characters")
    fun searchCharacter(@Query("limit") limit: Int, @Query("offset") offset: Int, @Query("nameStartsWith") searchQuery: String? = null): Single<CharactersResponse>

}