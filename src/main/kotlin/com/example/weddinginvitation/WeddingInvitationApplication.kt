package com.example.weddinginvitation

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class WeddingInvitationApplication

fun main(args: Array<String>) {
    runApplication<WeddingInvitationApplication>(*args)
}
