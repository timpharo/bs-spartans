package com.example.bspicvote

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class KVDbService(val restTemplate: RestTemplate, @Value("\${kvdb.bucketUrl}") val bucketUrl: String) {

    fun incrementKey(key: String) {
        val postUrl = "$bucketUrl/$key"
        println("Posting increment request for key [$key] to url [$postUrl]")

        restTemplate
                .patchForObject(postUrl, "+1", String::class.java)

        println("Post to increment key [$key] returned response")
    }

}