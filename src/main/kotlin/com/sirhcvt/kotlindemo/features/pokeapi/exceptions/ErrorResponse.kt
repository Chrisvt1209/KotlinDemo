package com.sirhcvt.kotlindemo.features.pokeapi.exceptions

data class ErrorResponse(
    val timestamp: String,
    val error: String,
    val statusCode: Int,
    val message: String
)
