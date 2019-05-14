package com.example.bspicvote

import org.jboss.logging.Logger
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class KVDbService(val restTemplate: RestTemplate, @Value("\${kvdb.bucketUrl}") val bucketUrl: String) {

    val log = Logger.getLogger(javaClass)!!

    fun incrementKey(key: String) {
        val postUrl = "$bucketUrl/$key"
        log.info("Posting increment request for key [$key]")

        restTemplate.patchForObject(postUrl, "+1", String::class.java)

        log.info("Post to increment key [$key] returned response")
    }

}