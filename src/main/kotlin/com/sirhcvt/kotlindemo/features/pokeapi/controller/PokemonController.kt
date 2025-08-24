package com.sirhcvt.kotlindemo.features.pokeapi.controller

import com.sirhcvt.kotlindemo.features.pokeapi.client.PokemonClient
import com.sirhcvt.kotlindemo.features.pokeapi.models.Pokemon
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PokemonController(
    private val pokemonClient: PokemonClient
) {

    @GetMapping("/pokemon/{id}")
    suspend fun getPokemonDetails(@PathVariable id: String): ResponseEntity<Pokemon> {
        return pokemonClient.getPokemonDetails(id)
    }
}