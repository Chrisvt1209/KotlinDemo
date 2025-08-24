package com.sirhcvt.kotlindemo.features.pokeapi.models

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize

@JsonSerialize
data class Pokemon(
    @JsonProperty("id")
    val id: Int?,

    @JsonProperty("name")
    val name: String?,

    @JsonProperty("weight")
    val weight: Int?,

    @JsonProperty("height")
    val height: Int?,

    @JsonProperty("sprites")
    val sprites: Sprites?,

    @JsonProperty("stats")
    val stats: List<Stat?>,

    @JsonProperty("types")
    val types: List<Type?>
)
