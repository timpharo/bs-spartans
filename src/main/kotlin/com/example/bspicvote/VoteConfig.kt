package com.example.bspicvote

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate

@Configuration
class VoteConfig {

    @Bean
    fun restTemplate(requestFactory: HttpComponentsClientHttpRequestFactory): RestTemplate {
        val restTemplate = RestTemplate(requestFactory)
        restTemplate.requestFactory = requestFactory
        return restTemplate
    }


    @Bean
    fun httpComponentsClientHttpRequestFactory(): HttpComponentsClientHttpRequestFactory {
        val requestFactory = HttpComponentsClientHttpRequestFactory()
        requestFactory.setConnectTimeout(0)
        requestFactory.setReadTimeout(0)
        return requestFactory
    }
}