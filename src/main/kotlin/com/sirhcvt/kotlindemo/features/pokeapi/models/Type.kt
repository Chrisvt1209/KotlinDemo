package com.sirhcvt.kotlindemo.features.pokeapi.models

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize

@JsonSerialize
data class Type(
    @JsonProperty("slot")
    val slot: Int,

    @JsonProperty("type")
    val typeDetail: TypeDetail
)
