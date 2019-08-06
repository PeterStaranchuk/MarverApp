package com.ps.superheroapp.ui.character_screen.list

import android.widget.Filter
import android.widget.Filterable
import com.airbnb.epoxy.EpoxyController
import javax.inject.Inject

class CharacterController @Inject constructor() : EpoxyController(), Filterable {

    private var characters: List<Character> = listOf()
    private var filteredList = listOf<Character>()

    fun setCharacters(characters: List<Character>) {
        this.characters = characters
        this.filteredList = characters
        requestModelBuild()
    }

    override fun buildModels() {
        for (character in filteredList) {
            CharacterEpoxyModel(character).addTo(this)
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(searchString: CharSequence?): FilterResults {
                return FilterResults().apply {
                    filteredList = if (searchString.isNullOrEmpty()) {
                        characters
                    } else {
                        characters.filter { it.name.contains(searchString) }
                    }
                    values = filteredList
                }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                requestModelBuild()
            }
        }
    }
}