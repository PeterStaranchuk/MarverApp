package com.ps.superheroapp.ui.character_screen

import com.ps.superheroapp.di.modules.CharactersPageModule
import dagger.BindsInstance
import dagger.Subcomponent


@Subcomponent(modules = [CharactersModule::class, CharactersPageModule::class])
interface CharacterSubcomponent {
    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun with(fragment: CharactersFragment): Builder

        fun build(): CharacterSubcomponent
    }

    fun inject(fragment: CharactersFragment)
}