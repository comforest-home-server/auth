package com.comforest.core.auth.social.naver

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory

@Configuration
class NaverConfiguration {
    @Bean
    internal fun naverOpenApiClient(): NaverOpenApiClient {
        val webClient = WebClient.builder().baseUrl(NAVER_OPEN_API_URL).build()
        val factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient)).build()
        return factory.createClient(NaverOpenApiClient::class.java)
    }

    companion object {
        private const val NAVER_OPEN_API_URL = "https://openapi.naver.com"
    }
}
