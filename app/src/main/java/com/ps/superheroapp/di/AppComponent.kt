package com.ps.superheroapp.di

import android.app.Application
import com.ps.superheroapp.ui.character_screen.CharacterSubcomponent
import com.ps.superheroapp.ui.character_screen.CharactersModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [CharactersModule::class])
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun withApplication(application: Application): Builder

        fun build(): AppComponent
    }

    fun characterSubcomponent(): CharacterSubcomponent.Builder
}