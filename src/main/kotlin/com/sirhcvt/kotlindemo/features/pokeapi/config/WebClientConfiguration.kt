package com.sirhcvt.kotlindemo.features.pokeapi.config

import io.netty.channel.ChannelOption
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import java.util.concurrent.TimeUnit

@Configuration
class WebClientConfiguration(
    @Value("\${api.base.url}") private val baseUrl: String,
    @Value("\${api.timeout}") private val timeout: Int
) {

    @Bean
    fun webClientWithTimeout(): WebClient {
        val httpClient = HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, timeout)
            .doOnConnected { connection ->
                connection.addHandlerLast(ReadTimeoutHandler(timeout.toLong(), TimeUnit.MILLISECONDS))
                connection.addHandlerLast(WriteTimeoutHandler(timeout.toLong(), TimeUnit.MILLISECONDS))
            }

        return WebClient.builder()
            .baseUrl(baseUrl)
            .codecs { config ->
                config.defaultCodecs().maxInMemorySize(2 * 1024 * 1024)
            }
            .clientConnector(ReactorClientHttpConnector(httpClient))
            .build()
    }
}