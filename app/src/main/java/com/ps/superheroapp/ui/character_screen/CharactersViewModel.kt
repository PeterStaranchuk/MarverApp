package com.ps.superheroapp.ui.character_screen

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ps.superheroapp.objects.*
import com.ps.superheroapp.ui.character_screen.list.Character
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class CharactersViewModel @Inject constructor(
    private val interactor: CharactersContract.Interactor,
    private val mainScheduler: Scheduler,
    private val connectivityChecker: ConnectivityChecker
) : ViewModel() {

    val onScreen = PublishSubject.create<Screen>()
    val filter = MutableLiveData<String>()
    val characters = MutableLiveData<Array<Character>>()
    val compositDisposable = CompositeDisposable()
    val error = ObservableField<ErrorType>()
    val loaderVisibility = ObservableField(ViewVisibility.GONE)

    fun fetchCharacters() {
        compositDisposable.add(
            interactor.getCharacters()
                .doOnSubscribe {
                    loaderVisibility.set(ViewVisibility.VISIBLE)
                }
                .doFinally {
                    loaderVisibility.set(ViewVisibility.GONE)
                }
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

    fun openCharacterInformationScreen(characterId: Long) {
        val screenToOpen = Screen.CHARACTER_DETAIL_INFO
        screenToOpen.payload[Extras.CHARACTER_ID] = characterId
        onScreen.onNext(screenToOpen)
    }
}