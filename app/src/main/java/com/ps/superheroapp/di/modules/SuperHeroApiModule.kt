package com.ps.superheroapp.di.modules

import com.ps.superheroapp.api.MarvelApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class SuperHeroApiModule {

    @Provides
    fun provideSuperheroApi(retrofit: Retrofit): MarvelApiService {
        return retrofit.create(MarvelApiService::class.java)
    }
}