package com.ps.superheroapp.ui.character_screen

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class CharactersViewModel @Inject constructor(val interactor: CharactersContract.Interactor) : ViewModel()