package com.ps.superheroapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ps.superheroapp.objects.*
import com.ps.superheroapp.ui.character_screen.CharactersInteractorImpl
import com.ps.superheroapp.ui.character_screen.CharactersViewModel
import com.ps.superheroapp.ui.character_screen.list.Character
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.TestObserver
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations

class CharactersScreenTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var connectivityChecker: ConnectivityCheckerImpl

    lateinit var vm: CharactersViewModel

    @Mock
    lateinit var interactor: CharactersInteractorImpl

    @Mock
    lateinit var disposable: CompositeDisposable

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        vm = CharactersViewModel(interactor, connectivityChecker, disposable)
    }

    @Test
    fun should_show_list_of_characters_when_screen_started() {
        `when`(interactor.observeCharactersLoadEvents()).then {
            Observable.create<CharacterLoadEvent> { emitter ->
                emitter.onNext(CharacterLoadEvent.INITIAL_LOAD_STARTED)
                emitter.onNext(CharacterLoadEvent.LOADED)
            }
        }
        val list = TestPageList.get<Character>(
            listOf(
                Character(name = "SpiderMan", id = 1),
                Character(name = "Hulk", id = 2),
                Character(name = "Tor", id = 3)
            )
        )

        `when`(interactor.getCharacters()).thenReturn(list)

        vm.fetchCharacters()

        Assert.assertEquals(3, vm.onCharactersLoaded().value?.size)
    }

    @Test
    fun should_show_network_error_when_screen_data_cannot_be_loaded_because_of_internet_connection() {
        `when`(connectivityChecker.isOffline()).thenReturn(true)
        `when`(interactor.observeCharactersLoadEvents()).thenReturn(Observable.just(CharacterLoadEvent.INITIAL_LOAD_FAILED))
        `when`(interactor.getCharacters()).then {
            TestPageList.get<Character>(listOf())
        }

        vm.fetchCharacters()

        Assert.assertEquals(ErrorType.NETWORK, vm.error.get())
    }

    @Test
    fun should_show_general_error_when_screen_data_cannot_be_loaded_because_of_unknown_error() {
        `when`(connectivityChecker.isOffline()).thenReturn(false)
        `when`(interactor.observeCharactersLoadEvents()).thenReturn(Observable.just(CharacterLoadEvent.INITIAL_LOAD_FAILED))

        vm.fetchCharacters()
        Assert.assertEquals(ErrorType.GENERAL, vm.error.get())
    }

    @Test
    fun should_filter_character_list_when_user_enter_text_in_search_field() {
        `when`(interactor.observeCharactersLoadEvents()).thenReturn(Observable.just(CharacterLoadEvent.INITIAL_LOAD_STARTED))
        `when`(interactor.getCharacters()).then {
            TestPageList.get<Character>(listOf())
        }

        vm.searchQuery.value = "Hulk"

        Mockito.verify(interactor, times(1)).getCharacters("Hulk")
    }


    @Test
    fun should_open_detail_information_screen_when_user_clicked_item_in_character_list() {
        val testSubscriber = TestObserver<Screen>()
        vm.onOpenScreen().subscribe(testSubscriber)

        val characterId = 100L
        vm.openCharacterInformationScreen(characterId)

        testSubscriber.assertValue {
            it == Screen.CHARACTER_DETAIL_INFO && it.payload[Extras.CHARACTER_ID] == characterId
        }
    }

    @Test
    fun should_show_progress_bar_when_character_list_loading() {
        `when`(interactor.observeCharactersLoadEvents()).thenReturn(Observable.just(CharacterLoadEvent.INITIAL_LOAD_STARTED))
        val list = TestPageList.get<Character>(
            listOf(
                Character(name = "SpiderMan", id = 1),
                Character(name = "Hulk", id = 2),
                Character(name = "Tor", id = 3)
            )
        )

        `when`(interactor.getCharacters()).thenReturn(list)

        vm.fetchCharacters()
        Assert.assertEquals(ViewVisibility.VISIBLE, vm.loaderVisibility.get())
    }

    @Test
    fun should_hide_progress_when_network_error_occurred() {
        `when`(connectivityChecker.isOffline()).thenReturn(true)
        `when`(interactor.observeCharactersLoadEvents()).then {
            Observable.create<CharacterLoadEvent> { emitter ->
                emitter.onNext(CharacterLoadEvent.INITIAL_LOAD_FAILED)
                emitter.onNext(CharacterLoadEvent.LOADED)
            }
        }

        vm.fetchCharacters()

        Assert.assertEquals(ViewVisibility.GONE, vm.loaderVisibility.get())
    }

    @Test
    fun should_hide_progress_when_general_error_occurred() {
        `when`(connectivityChecker.isOffline()).thenReturn(false)
        `when`(interactor.observeCharactersLoadEvents()).then {
            Observable.create<CharacterLoadEvent> { emitter ->
                emitter.onNext(CharacterLoadEvent.INITIAL_LOAD_FAILED)
                emitter.onNext(CharacterLoadEvent.LOADED)
            }
        }

        vm.fetchCharacters()

        Assert.assertEquals(ViewVisibility.GONE, vm.loaderVisibility.get())
    }

    @Test
    fun should_hide_progress_when_character_data_loaded() {
        `when`(interactor.observeCharactersLoadEvents()).then {
            Observable.create<CharacterLoadEvent> { emitter ->
                emitter.onNext(CharacterLoadEvent.INITIAL_LOAD_STARTED)
                emitter.onNext(CharacterLoadEvent.LOADED)
            }
        }
        val list = TestPageList.get<Character>(
            listOf(
                Character(name = "SpiderMan", id = 1),
                Character(name = "Hulk", id = 2),
                Character(name = "Tor", id = 3)
            )
        )
        `when`(interactor.getCharacters()).thenReturn(list)

        vm.fetchCharacters()
        Assert.assertEquals(ViewVisibility.GONE, vm.loaderVisibility.get())
    }

    @Test
    fun should_hide_error_when_loading_started() {
        `when`(interactor.observeCharactersLoadEvents()).then {
            Observable.create<CharacterLoadEvent> { emitter ->
                emitter.onNext(CharacterLoadEvent.INITIAL_LOAD_STARTED)
            }
        }
        val list = TestPageList.get<Character>(
            listOf(
                Character(name = "SpiderMan", id = 1),
                Character(name = "Hulk", id = 2),
                Character(name = "Tor", id = 3)
            )
        )
        `when`(interactor.getCharacters()).thenReturn(list)

        vm.fetchCharacters()

        Assert.assertEquals(null, vm.error.get())
    }

    @Test
    fun should_show_bottom_load_indicator_when_next_page_loading() {
        `when`(interactor.observeCharactersLoadEvents()).then {
            Observable.create<CharacterLoadEvent> { emitter ->
                emitter.onNext(CharacterLoadEvent.NEXT_PAGE_LOAD_STARTED)
            }
        }
        val list = TestPageList.get<Character>(
            listOf(
                Character(name = "SpiderMan", id = 1),
                Character(name = "Hulk", id = 2),
                Character(name = "Tor", id = 3)
            )
        )
        `when`(interactor.getCharacters()).thenReturn(list)
        vm.fetchCharacters()
        Assert.assertEquals(ViewVisibility.VISIBLE, vm.nextPageLoaderVisibility.get())
    }

    @Test
    fun should_hide_error_when_next_page_load_started() {
        `when`(interactor.observeCharactersLoadEvents()).then {
            Observable.create<CharacterLoadEvent> { emitter ->
                emitter.onNext(CharacterLoadEvent.NEXT_PAGE_LOAD_STARTED)
            }
        }
        val list = TestPageList.get<Character>(
            listOf(
                Character(name = "SpiderMan", id = 1),
                Character(name = "Hulk", id = 2),
                Character(name = "Tor", id = 3)
            )
        )
        `when`(interactor.getCharacters()).thenReturn(list)
        vm.fetchCharacters()

        Assert.assertEquals(null, vm.error.get())
    }

    @Test
    fun should_hide_bottom_loader_and_show_try_again_if_new_page_not_loaded_because_of_network_error() {
        `when`(connectivityChecker.isOffline()).thenReturn(true)

        `when`(interactor.observeCharactersLoadEvents()).then {
            Observable.create<CharacterLoadEvent> { emitter ->
                emitter.onNext(CharacterLoadEvent.NEXT_PAGE_LOAD_STARTED)
                emitter.onNext(CharacterLoadEvent.NEXT_PAGE_LOAD_FAILED)
            }
        }
        val list = TestPageList.get<Character>(
            listOf(
                Character(name = "SpiderMan", id = 1),
                Character(name = "Hulk", id = 2),
                Character(name = "Tor", id = 3)
            )
        )
        `when`(interactor.getCharacters()).thenReturn(list)
        vm.fetchCharacters()

        Assert.assertEquals(ViewVisibility.VISIBLE, vm.tryAgainVisibility.get())
    }

    @Test
    fun should_hide_bottom_loader_and_show_try_again_if_new_page_not_loaded_because_of_general_error() {
        `when`(connectivityChecker.isOffline()).thenReturn(false)

        `when`(interactor.observeCharactersLoadEvents()).then {
            Observable.create<CharacterLoadEvent> { emitter ->
                emitter.onNext(CharacterLoadEvent.NEXT_PAGE_LOAD_STARTED)
                emitter.onNext(CharacterLoadEvent.NEXT_PAGE_LOAD_FAILED)
            }
        }
        val list = TestPageList.get<Character>(
            listOf(
                Character(name = "SpiderMan", id = 1),
                Character(name = "Hulk", id = 2),
                Character(name = "Tor", id = 3)
            )
        )
        `when`(interactor.getCharacters()).thenReturn(list)
        vm.fetchCharacters()

        Assert.assertEquals(ViewVisibility.VISIBLE, vm.tryAgainVisibility.get())
    }

    @Test
    fun should_hide_try_again_state_if_user_pressed_try_again() {
        `when`(interactor.observeCharactersLoadEvents()).then {
            Observable.create<CharacterLoadEvent> { emitter ->
                emitter.onNext(CharacterLoadEvent.NEXT_PAGE_LOAD_STARTED)
            }
        }
        val list = TestPageList.get<Character>(
            listOf(
                Character(name = "SpiderMan", id = 1),
                Character(name = "Hulk", id = 2),
                Character(name = "Tor", id = 3)
            )
        )
        `when`(interactor.getCharacters()).thenReturn(list)

        vm.onTryAgainPressed()

        Assert.assertEquals(ViewVisibility.GONE, vm.tryAgainVisibility.get())
    }

    @Test
    fun should_load_new_page_again_if_user_pressed_try_again() {
        `when`(interactor.observeCharactersLoadEvents()).then {
            Observable.create<CharacterLoadEvent> { emitter ->
                emitter.onNext(CharacterLoadEvent.NEXT_PAGE_LOAD_STARTED)
            }
        }
        val list = TestPageList.get<Character>(
            listOf(
                Character(name = "SpiderMan", id = 1),
                Character(name = "Hulk", id = 2),
                Character(name = "Tor", id = 3)
            )
        )
        `when`(interactor.getCharacters()).thenReturn(list)

        vm.onTryAgainPressed()

        Mockito.verify(interactor, times(1)).getCharacters()
    }

}