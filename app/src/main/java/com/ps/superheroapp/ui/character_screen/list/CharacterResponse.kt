package com.ps.superheroapp.ui.character_screen.list


import com.google.gson.annotations.SerializedName

class Connections(
    @SerializedName("relatives") val relatives: String = "",
    @SerializedName("group-affiliation") val groupAffiliation: String = ""
)

class Character(
    @SerializedName("image") val image: Image = Image(""),
    @SerializedName("appearance") val appearance: Appearance = Appearance(),
    @SerializedName("work") val work: Work = Work(),
    @SerializedName("name") val name: String = "",
    @SerializedName("powerstats") val powerstats: Powerstats = Powerstats(),
    @SerializedName("id") val id: String = "",
    @SerializedName("biography") val biography: Biography = Biography(),
    @SerializedName("connections") val connections: Connections = Connections()
)

class Work(
    @SerializedName("occupation") val occupation: String = "",
    @SerializedName("base") val base: String = ""
)

class Biography(
    @SerializedName("place-of-birth") val placeOfBirth: String = "",
    @SerializedName("aliases") val aliases: Array<String>? = arrayOf(),
    @SerializedName("first-appearance") val firstAppearance: String = "",
    @SerializedName("publisher") val publisher: String = "",
    @SerializedName("alignment") val alignment: String = "",
    @SerializedName("full-name") val fullName: String = "",
    @SerializedName("alter-egos") val alterEgos: String = ""
)


class Powerstats(
    @SerializedName("strength") val strength: String = "",
    @SerializedName("durability") val durability: String = "",
    @SerializedName("combat") val combat: String = "",
    @SerializedName("power") val power: String = "",
    @SerializedName("speed") val speed: String = "",
    @SerializedName("intelligence") val intelligence: String = ""
)

class Image(@SerializedName("url") val url: String = "")

class Appearance(
    @SerializedName("eye-color") val eyeColor: String = "",
    @SerializedName("gender") val gender: String = "",
    @SerializedName("race") val race: String = "",
    @SerializedName("weight") val weight: Array<String>? = arrayOf(),
    @SerializedName("height") val height: Array<String>? = arrayOf(),
    @SerializedName("hair-color") val hairColor: String = ""
)


class CharacterResponse(
    @SerializedName("results-for") val resultsFor: String = "",
    @SerializedName("response") val response: String = "",
    @SerializedName("results") val characters: Array<Character>?
)


