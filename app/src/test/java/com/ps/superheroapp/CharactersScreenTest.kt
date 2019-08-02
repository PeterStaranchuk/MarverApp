package com.ps.superheroapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ps.superheroapp.objects.*
import com.ps.superheroapp.ui.character_screen.CharactersInteractorImpl
import com.ps.superheroapp.ui.character_screen.CharactersViewModel
import com.ps.superheroapp.ui.character_screen.list.Character
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.util.concurrent.TimeUnit

class CharactersScreenTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var interactor: CharactersInteractorImpl

    @Mock
    lateinit var connectivityChecker: ConnectivityCheckerImpl

    lateinit var vm: CharactersViewModel

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        vm = CharactersViewModel(interactor, Schedulers.trampoline(), connectivityChecker)
    }

    @Test
    fun should_show_list_of_characters_when_screen_started() {
        `when`(interactor.getCharacters()).thenReturn(
            Single.just(
                arrayOf(
                    Character(name = "SpiderMan", id = 1),
                    Character(name = "Hulk", id = 2),
                    Character(name = "Tor", id = 3)
                )
            )
        )

        vm.fetchCharacters()

        Assert.assertEquals(3, vm.onCharactersLoaded().value?.size)
    }

    @Test
    fun should_show_network_error_when_screen_data_cannot_be_loaded_because_of_internet_connection() {
        `when`(connectivityChecker.isOffline()).thenReturn(true)
        `when`(interactor.getCharacters()).thenReturn(Single.error(Throwable()))

        vm.fetchCharacters()
        Assert.assertEquals(ErrorType.NETWORK, vm.error.get())
    }

    @Test
    fun should_show_general_error_when_screen_data_cannot_be_loaded_because_of_unknown_error() {
        `when`(connectivityChecker.isOffline()).thenReturn(false)
        `when`(interactor.getCharacters()).thenReturn(Single.error(Throwable()))

        vm.fetchCharacters()
        Assert.assertEquals(ErrorType.GENERAL, vm.error.get())
    }

    @Test
    fun should_filter_character_list_when_user_enter_text_in_search_field() {
        vm.filterCharacters("Hulk")

        Assert.assertEquals("Hulk", vm.onFilterChanged().value)
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
        `when`(interactor.getCharacters()).thenReturn(
            Single.just(
                arrayOf(
                    Character(name = "SpiderMan", id = 0),
                    Character(name = "Hulk", id = 1),
                    Character(name = "Tor", id = 2)
                )
            ).delay(10, TimeUnit.SECONDS)
        )
        vm.fetchCharacters()
        Assert.assertEquals(vm.loaderVisibility.get(), ViewVisibility.VISIBLE)
    }

    @Test
    fun should_hide_progress_when_network_error_occurred() {
        `when`(connectivityChecker.isOffline()).thenReturn(true)
        `when`(interactor.getCharacters()).thenReturn(Single.error(Throwable()))

        vm.fetchCharacters()

        Assert.assertEquals(vm.loaderVisibility.get(), ViewVisibility.GONE)
    }

    @Test
    fun should_hide_progress_when_general_error_occurred() {
        `when`(connectivityChecker.isOffline()).thenReturn(false)
        `when`(interactor.getCharacters()).thenReturn(Single.error(Throwable()))

        vm.fetchCharacters()

        Assert.assertEquals(vm.loaderVisibility.get(), ViewVisibility.GONE)
    }

    @Test
    fun should_hide_progress_when_character_data_loaded() {
        `when`(interactor.getCharacters()).thenReturn(
            Single.just(
                arrayOf(
                    Character(name = "SpiderMan", id = 1),
                    Character(name = "Hulk", id = 2),
                    Character(name = "Tor", id = 3)
                )
            )
        )
        vm.fetchCharacters()
        Assert.assertEquals(vm.loaderVisibility.get(), ViewVisibility.GONE)
    }

    @Test
    fun should_hide_error_when_loading_started() {
        `when`(interactor.getCharacters()).thenReturn(
            Single.just(
                arrayOf(
                    Character(name = "SpiderMan", id = 1),
                    Character(name = "Hulk", id = 2),
                    Character(name = "Tor", id = 3)
                )
            )
        )
        vm.fetchCharacters()
        Assert.assertEquals(vm.error.get(), null)
    }
}