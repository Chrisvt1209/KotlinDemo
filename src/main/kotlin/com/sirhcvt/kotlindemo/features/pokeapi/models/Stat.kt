package com.sirhcvt.kotlindemo.features.pokeapi.models

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize

@JsonSerialize
data class Stat(
    @JsonProperty("base_stat")
    val baseStat: Int,

    @JsonProperty("effort")
    val effort: Int,

    @JsonProperty("stat")
    val statDetail: StatDetail
)
