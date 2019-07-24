package com.ps.superheroapp.ui.character_screen

import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides

@Module
class CharactersModule {

    @Provides
    fun productViewModel(fragment: CharactersFragment): CharactersViewModel {
        return ViewModelProviders.of(fragment).get(CharactersViewModel::class.java)
    }

    @Provides
    fun characterInteractor() : CharactersInteractorImpl {
        return CharactersInteractorImpl()
    }

}