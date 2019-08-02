package com.ps.superheroapp.ui.character_screen.list

import com.airbnb.epoxy.EpoxyController
import javax.inject.Inject

class CharacterController @Inject constructor() : EpoxyController() {

    private var characters: Array<Character> = arrayOf()

    fun setCharacters(characters: Array<Character>) {
        this.characters = characters
        requestModelBuild()
    }

    override fun buildModels() {
        for (character in characters) {
            CharacterEpoxyModel(character).addTo(this)
        }
    }
}