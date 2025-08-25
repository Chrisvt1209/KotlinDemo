package com.sirhcvt.kotlindemo.features.pokeapi.controller

import com.sirhcvt.kotlindemo.features.pokeapi.client.PokemonApiService
import com.sirhcvt.kotlindemo.features.pokeapi.dto.PokemonListItem
import com.sirhcvt.kotlindemo.features.pokeapi.models.Pokemon
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/pokemon")
class PokemonController(
    private val apiService: PokemonApiService
) {
    @GetMapping
    suspend fun getPokemonList(
        @RequestParam(required = false, defaultValue = "20") limit: Int,
        @RequestParam(required = false, defaultValue = "0") offset: Int
    ): ResponseEntity<List<PokemonListItem>> {
        val pokemonList = apiService.getPokemonList(limit, offset)
        return ResponseEntity.ok(pokemonList)
    }

    @GetMapping("/{id}")
    suspend fun getPokemonDetails(@PathVariable id: String): ResponseEntity<Pokemon> {
        val pokemon = apiService.getPokemonDetails(id)
        return ResponseEntity.ok(pokemon)
    }
}