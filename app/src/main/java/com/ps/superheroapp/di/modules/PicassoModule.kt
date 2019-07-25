package com.ps.superheroapp.di.modules

import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PicassoModule {

    @Provides
    @Singleton
    fun providePicasso(): Picasso {
        return Picasso.get()
    }
}