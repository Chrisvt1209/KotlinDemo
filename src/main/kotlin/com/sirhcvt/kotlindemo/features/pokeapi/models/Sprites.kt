package com.sirhcvt.kotlindemo.features.pokeapi.models

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize

@JsonSerialize
data class Sprites(
    @JsonProperty("front_default")
    val frontDefault: String?,

    @JsonProperty("back_default")
    val backDefault: String?
)
