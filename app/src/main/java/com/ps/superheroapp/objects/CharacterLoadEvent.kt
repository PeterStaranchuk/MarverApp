package com.ps.superheroapp.objects

enum class CharacterLoadEvent {
    INITIAL_LOAD_STARTED,
    NEXT_PAGE_LOAD_STARTED,
    LOADED,
    INITIAL_LOAD_FAILED,
    NEXT_PAGE_LOAD_FAILED
}