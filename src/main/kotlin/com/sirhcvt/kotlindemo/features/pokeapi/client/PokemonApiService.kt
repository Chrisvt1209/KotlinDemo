package com.sirhcvt.kotlindemo.features.pokeapi.client

import com.sirhcvt.kotlindemo.features.pokeapi.dto.PokemonListItem
import com.sirhcvt.kotlindemo.features.pokeapi.dto.PokemonListResponse
import com.sirhcvt.kotlindemo.features.pokeapi.models.Pokemon
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class PokemonApiService(
    private val client: WebClient
) {
    suspend fun getPokemonList(limit: Int, offset: Int): List<PokemonListItem> {
        val response = client.get()
            .uri { uriBuilder ->
                uriBuilder.path("pokemon")
                    .queryParam("limit", limit)
                    .queryParam("offset", offset)
                    .build()
            }
            .retrieve()
            .bodyToMono(PokemonListResponse::class.java)
            .awaitSingle()

        return response.results
    }

    suspend fun getPokemonDetails(id: String): Pokemon {
        require(id.isNotBlank()) { "Pokemon id must not be blank" }
        return client.get()
            .uri("pokemon/{id}", id)
            .retrieve()
            .bodyToMono(Pokemon::class.java)
            .awaitSingle()
    }
}