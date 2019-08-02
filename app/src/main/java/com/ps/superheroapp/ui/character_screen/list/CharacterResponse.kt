package com.ps.superheroapp.ui.character_screen.list

class Series(
    val collectionURI: String = "",
    val available: Int = 0,
    val returned: Int = 0,
    val items: Array<ItemsItem>? = arrayOf()
)

class Events(
    val collectionURI: String = "",
    val available: Int = 0,
    val returned: Int = 0,
    val items: Array<ItemsItem>? = arrayOf()
)

class Character(
    val thumbnail: Thumbnail = Thumbnail(),
    val urls: Array<UrlsItem>? = arrayOf(),
    val stories: Stories = Stories(),
    val series: Series = Series(),
    val comics: Comics = Comics(),
    val name: String = "",
    val description: String = "",
    val modified: String = "",
    val id: Long = 0,
    val resourceURI: String = "",
    val events: Events = Events()
)

class CharactersResponse(
    val copyright: String = "",
    val code: Int = 0,
    val data: Data,
    val attributionHTML: String = "",
    val attributionText: String = "",
    val etag: String = "",
    val status: String = ""
)

class UrlsItem(
    val type: String = "",
    val url: String = ""
)

class ItemsItem(
    val name: String = "",
    val resourceURI: String = ""
)

class Data(
    val total: Int = 0,
    val offset: Int = 0,
    val limit: Int = 0,
    val count: Int = 0,
    val results: Array<Character>?
)

class Stories(
    val collectionURI: String = "",
    val available: Int = 0,
    val returned: Int = 0,
    val items: Array<ItemsItem>? = arrayOf()
)


class Thumbnail(
    val path: String = "",
    val extension: String = ""
)

class Comics(
    val collectionURI: String = "",
    val available: Int = 0,
    val returned: Int = 0,
    val items: Array<ItemsItem>? = arrayOf()
)