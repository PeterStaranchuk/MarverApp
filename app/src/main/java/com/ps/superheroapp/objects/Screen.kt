package com.ps.superheroapp.objects

enum class Screen(var payload: HashMap<String, Any> = hashMapOf()) {
    CHARACTERS_LIST, CHARACTER_DETAIL_INFO
}