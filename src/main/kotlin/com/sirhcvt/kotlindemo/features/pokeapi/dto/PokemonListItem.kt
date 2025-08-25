package com.sirhcvt.kotlindemo.features.pokeapi.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class PokemonListItem(
    @JsonProperty("name")
    val name: String,

    @JsonProperty("url")
    val url: String
)
