package com.ps.superheroapp.ui.character_screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ps.superheroapp.ui.character_screen.list.Character
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class CharactersViewModel @Inject constructor(
    private val interactor: CharactersContract.Interactor,
    private val mainScheduler: Scheduler
) : ViewModel() {

    val characters = MutableLiveData<Array<Character>>()
    val compositDisposable = CompositeDisposable()

    fun fetchCharacters() {
        compositDisposable.add(
            interactor.getCharacters()
                .observeOn(mainScheduler)
                .subscribe({
                    characters.value = it
                }, {
                    //TODO handle error
                })
        )
    }
}