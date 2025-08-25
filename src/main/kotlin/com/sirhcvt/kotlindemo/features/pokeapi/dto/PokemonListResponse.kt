package com.sirhcvt.kotlindemo.features.pokeapi.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class PokemonListResponse(
    @JsonProperty("count")
    val count: Int,

    @JsonProperty("next")
    val next: String?,

    @JsonProperty("previous")
    val previous: String?,

    @JsonProperty("results")
    val results: List<PokemonListItem>
)
