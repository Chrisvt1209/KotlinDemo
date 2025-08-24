package com.sirhcvt.kotlindemo.features.pokeapi.models

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize

@JsonSerialize
data class StatDetail(
    @JsonProperty("name")
    val name: String,

    @JsonProperty("url")
    val url: String
)
