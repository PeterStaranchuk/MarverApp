package com.ps.superheroapp.ui.character_screen

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.ps.superheroapp.objects.*
import com.ps.superheroapp.ui.character_screen.list.Character
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class CharactersViewModel @Inject constructor(
        private val interactor: CharactersContract.Interactor,
        private val connectivityChecker: ConnectivityChecker,
        private val disposable: CompositeDisposable
) : RestorableLoadViewModel() {

    private val onScreen = PublishSubject.create<Screen>()
    val searchQuery = MutableLiveData<String>()
    private val characters = MutableLiveData<PagedList<Character>>()
    val loaderVisibility = ObservableField(ViewVisibility.VISIBLE)
    val nextPageLoaderVisibility = ObservableField(ViewVisibility.GONE)

    private val filterCharactersObserver = Observer<String> {
        fetchCharacters()
    }

    init {
        searchQuery.observeForever(filterCharactersObserver)
    }

    fun fetchCharacters() {
        characters.value = interactor.getCharacters(searchQuery.value)
        disposable.add(interactor.observeCharactersLoadEvents().subscribe {
            it?.let { event ->
                when (event) {
                    CharacterLoadEvent.INITIAL_LOAD_STARTED -> initialLoadStarted()
                    CharacterLoadEvent.LOADED -> loadFinished()
                    CharacterLoadEvent.INITIAL_LOAD_FAILED -> handleError()
                    CharacterLoadEvent.NEXT_PAGE_LOAD_STARTED -> nextPageLoadStarted()
                    CharacterLoadEvent.NEXT_PAGE_LOAD_FAILED -> nextPageLoadFailed()
                }
            }
        })
    }

    private fun nextPageLoadFailed() {
        tryAgainVisibility.set(ViewVisibility.VISIBLE)
    }

    private fun nextPageLoadStarted() {
        clearError()
        nextPageLoaderVisibility.set(ViewVisibility.VISIBLE)
    }

    private fun handleError() {
        if (connectivityChecker.isOffline()) {
            error.set(ErrorType.NETWORK)
        } else {
            error.set(ErrorType.GENERAL)
        }
    }

    fun openCharacterInformationScreen(characterId: Long) {
        val screenToOpen = Screen.CHARACTER_DETAIL_INFO
        screenToOpen.payload[Extras.CHARACTER_ID] = characterId
        onScreen.onNext(screenToOpen)
    }

    fun onOpenScreen(): Observable<Screen> = onScreen

    fun onCharactersLoaded(): LiveData<PagedList<Character>> = characters

    private fun loadFinished() {
        loaderVisibility.set(ViewVisibility.GONE)
    }

    private fun initialLoadStarted() {
        clearError()
        loaderVisibility.set(ViewVisibility.VISIBLE)
    }

    private fun clearError() {
        error.set(null)
    }

    override fun performTryAgainAction() {
        fetchCharacters()
    }

    override fun onCleared() {
        searchQuery.removeObserver(filterCharactersObserver)
        disposable.dispose()
        super.onCleared()
    }
}