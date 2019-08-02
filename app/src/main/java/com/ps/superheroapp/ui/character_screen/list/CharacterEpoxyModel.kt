package com.ps.superheroapp.ui.character_screen.list

import androidx.databinding.ViewDataBinding
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.ps.superheroapp.BR
import com.ps.superheroapp.R

class CharacterEpoxyModel(val character: Character) : DataBindingEpoxyModel() {
    override fun getDefaultLayout() = R.layout.item_character

    init {
        id(character.id)
    }

    override fun setDataBindingVariables(binding: ViewDataBinding) {
        binding.setVariable(BR.character, character)
    }

}