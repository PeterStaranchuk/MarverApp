package com.ps.superheroapp.ui.character_screen.list


import com.google.gson.annotations.SerializedName

class Connections(@SerializedName("relatives") val relatives: String = "",
                  @SerializedName("group-affiliation") val groupAffiliation: String = "")

class ResultsItem(@SerializedName("image") val image: Image,
                  @SerializedName("appearance") val appearance: Appearance,
                  @SerializedName("work") val work: Work,
                  @SerializedName("name") val name: String = "",
                  @SerializedName("powerstats") val powerstats: Powerstats,
                  @SerializedName("id") val id: String = "",
                  @SerializedName("biography") val biography: Biography,
                  @SerializedName("connections") val connections: Connections)

class Work(@SerializedName("occupation") val occupation: String = "",
           @SerializedName("base") val base: String = ""
)

class Biography(@SerializedName("place-of-birth") val placeOfBirth: String = "",
                @SerializedName("aliases") val aliases: List<String>?,
                @SerializedName("first-appearance") val firstAppearance: String = "",
                @SerializedName("publisher") val publisher: String = "",
                @SerializedName("alignment") val alignment: String = "",
                @SerializedName("full-name") val fullName: String = "",
                @SerializedName("alter-egos") val alterEgos: String = "")


class Powerstats(@SerializedName("strength") val strength: String = "",
                 @SerializedName("durability") val durability: String = "",
                 @SerializedName("combat") val combat: String = "",
                 @SerializedName("power") val power: String = "",
                 @SerializedName("speed") val speed: String = "",
                 @SerializedName("intelligence") val intelligence: String = "")

class Image(@SerializedName("url") val url: String = "")

class Appearance(@SerializedName("eye-color") val eyeColor: String = "",
                 @SerializedName("gender") val gender: String = "",
                 @SerializedName("race") val race: String = "",
                 @SerializedName("weight") val weight: List<String>?,
                 @SerializedName("height") val height: List<String>?,
                 @SerializedName("hair-color") val hairColor: String = "")


class CharacterInfo(@SerializedName("results-for") val resultsFor: String = "",
                    @SerializedName("response") val response: String = "",
                    @SerializedName("results") val results: List<ResultsItem>?)


