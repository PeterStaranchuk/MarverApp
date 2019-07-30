package com.ps.superheroapp.di

import android.app.Application
import com.ps.superheroapp.SuperHeroApplication
import com.ps.superheroapp.di.modules.AppModule
import com.ps.superheroapp.di.modules.ConnectivityModule
import com.ps.superheroapp.di.modules.PicassoModule
import com.ps.superheroapp.di.modules.SchedulersModule
import com.ps.superheroapp.ui.character_screen.CharacterSubcomponent
import com.ps.superheroapp.ui.character_screen.CharactersModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class,
        AndroidInjectionModule::class,
        CharactersModule::class,
        PicassoModule::class,
        SchedulersModule::class,
        ConnectivityModule::class]
)
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun withApplication(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: SuperHeroApplication)
    fun characterSubcomponent(): CharacterSubcomponent.Builder
}