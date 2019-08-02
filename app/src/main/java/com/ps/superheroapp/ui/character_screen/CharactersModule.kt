package com.ps.superheroapp.ui.character_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aximetria.aximetria.di.view_model.DaggerViewModelFactory
import com.aximetria.aximetria.di.view_model.ViewModelKeyAnnotation
import com.ps.superheroapp.di.modules.SuperHeroApiModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [SuperHeroApiModule::class])
abstract class CharactersModule {

    @Binds
    @IntoMap
    @ViewModelKeyAnnotation.ViewModelKey(CharactersViewModel::class)
    abstract fun provideViewModel(charactersViewModel: CharactersViewModel): ViewModel

    @Binds
    abstract fun provideFactory(kotlinViewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory

    @Binds
    abstract fun provideInteractor(kotlinViewModelFactory: CharactersInteractorImpl): CharactersContract.Interactor
}