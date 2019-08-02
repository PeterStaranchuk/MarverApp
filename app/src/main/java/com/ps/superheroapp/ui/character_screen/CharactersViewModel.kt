package com.ps.superheroapp.ui.character_screen

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ps.superheroapp.objects.*
import com.ps.superheroapp.ui.character_screen.list.Character
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Named

class CharactersViewModel @Inject constructor(
        private val interactor: CharactersContract.Interactor,
        @Named(SchedulerNames.MAIN) private val mainScheduler: Scheduler,
        private val connectivityChecker: ConnectivityChecker
) : ViewModel() {

    private val onScreen = PublishSubject.create<Screen>()
    private val filter = MutableLiveData<String>()
    private val characters = MutableLiveData<Array<Character>>()
    private var disposable: Disposable? = null
    val error = ObservableField<ErrorType>()
    val loaderVisibility = ObservableField(ViewVisibility.VISIBLE)

    fun fetchCharacters() {
        disposable?.dispose()
        disposable = interactor.getCharacters()
                .observeOn(mainScheduler)
                .doOnSubscribe {
                    error.set(null)
                    loaderVisibility.set(ViewVisibility.VISIBLE)
                }
                .doFinally {
                    loaderVisibility.set(ViewVisibility.GONE)
                }
                .subscribe({
                    characters.value = it
                }, {
                    if (connectivityChecker.isOffline()) {
                        error.set(ErrorType.NETWORK)
                    } else {
                        error.set(ErrorType.GENERAL)
                    }
                })
    }

    fun filterCharacters(filter: String) {
        this.filter.value = filter
    }

    fun openCharacterInformationScreen(characterId: Long) {
        val screenToOpen = Screen.CHARACTER_DETAIL_INFO
        screenToOpen.payload[Extras.CHARACTER_ID] = characterId
        onScreen.onNext(screenToOpen)
    }

    fun onOpenScreen(): Observable<Screen> = onScreen

    fun onFilterChanged(): LiveData<String> = filter

    fun onCharactersLoaded(): LiveData<Array<Character>> = characters

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }
}