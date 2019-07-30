package com.ps.superheroapp.ui.character_screen

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ps.superheroapp.objects.ConnectivityChecker
import com.ps.superheroapp.objects.ErrorType
import com.ps.superheroapp.ui.character_screen.list.Character
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class CharactersViewModel @Inject constructor(
    private val interactor: CharactersContract.Interactor,
    private val mainScheduler: Scheduler,
    private val connectivityChecker: ConnectivityChecker
) : ViewModel() {

    val filter = MutableLiveData<String>()
    val characters = MutableLiveData<Array<Character>>()
    val compositDisposable = CompositeDisposable()
    val error = ObservableField<ErrorType>()

    fun fetchCharacters() {
        compositDisposable.add(
            interactor.getCharacters()
                .observeOn(mainScheduler)
                .subscribe({
                    characters.value = it
                }, {
                    if (connectivityChecker.isOffline()) {
                        error.set(ErrorType.NETWORK)
                    } else {
                        error.set(ErrorType.GENERAL)
                    }
                })
        )
    }

    fun filterCharacters(filter: String) {
        this.filter.value = filter
    }
}