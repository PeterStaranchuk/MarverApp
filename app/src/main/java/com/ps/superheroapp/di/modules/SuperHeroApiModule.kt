package com.ps.superheroapp.di.modules

import com.ps.superheroapp.api.SuperheroApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class SuperHeroApiModule {

    @Provides
    fun provideSuperheroApi(retrofit: Retrofit): SuperheroApi {
        return retrofit.create(SuperheroApi::class.java)
    }
}