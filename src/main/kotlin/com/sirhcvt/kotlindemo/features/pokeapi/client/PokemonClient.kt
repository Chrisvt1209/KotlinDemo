package com.sirhcvt.kotlindemo.features.pokeapi.client

import com.sirhcvt.kotlindemo.features.pokeapi.models.Pokemon
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class PokemonClient {
    private val client = WebClient.builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .codecs { config ->
            config.defaultCodecs().maxInMemorySize(2 * 1024 * 1024)
        }
        .build()

    suspend fun getPokemonDetails(id: String?): ResponseEntity<Pokemon> {
        return try {
            client.get()
                .uri("pokemon/{id}", id)
                .retrieve()
                .toEntity(Pokemon::class.java)
                .awaitSingle()
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseEntity.internalServerError().build()
        }
    }
}