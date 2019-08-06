package com.ps.superheroapp.ui.character_screen.list

data class Series(
    val collectionURI: String = "",
    val available: Int = 0,
    val returned: Int = 0,
    val items: List<ItemsItem>? = listOf()
)

data class Events(
    val collectionURI: String = "",
    val available: Int = 0,
    val returned: Int = 0,
    val items: List<ItemsItem>? = listOf()
)

data class Character(
    val thumbnail: Thumbnail = Thumbnail(),
    val urls: List<UrlsItem>? = listOf(),
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

data class CharactersResponse(
    val copyright: String = "",
    val code: Int = 0,
    val data: Data,
    val attributionHTML: String = "",
    val attributionText: String = "",
    val etag: String = "",
    val status: String = ""
)

data class UrlsItem(
    val type: String = "",
    val url: String = ""
)

data class ItemsItem(
    val name: String = "",
    val resourceURI: String = ""
)

data class Data(
    val total: Int = 0,
    val offset: Int = 0,
    val limit: Int = 0,
    val count: Int = 0,
    val results: List<Character>?
)

data class Stories(
    val collectionURI: String = "",
    val available: Int = 0,
    val returned: Int = 0,
    val items: List<ItemsItem>? = listOf()
)


data class Thumbnail(
    val path: String = "",
    val extension: String = ""
) {
    fun getAvatarUrl() = "$path.$extension".replace("http","https")
}

data class Comics(
    val collectionURI: String = "",
    val available: Int = 0,
    val returned: Int = 0,
    val items: List<ItemsItem>? = listOf()
)