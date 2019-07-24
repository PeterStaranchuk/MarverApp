package com.ps.superheroapp.ui.character_screen

import dagger.BindsInstance
import dagger.Subcomponent


@Subcomponent(modules = [CharactersModule::class])
interface CharacterSubcomponent {
    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun with(fragment: CharactersFragment): Builder

        fun build(): CharacterSubcomponent
    }

    fun inject(fragment: CharactersFragment)
}