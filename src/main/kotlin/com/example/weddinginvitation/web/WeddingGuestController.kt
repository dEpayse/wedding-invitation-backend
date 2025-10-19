package com.example.weddinginvitation.web

import com.example.weddinginvitation.dto.WeddingGuestRequest
import com.example.weddinginvitation.dto.WeddingGuestResponse
import com.example.weddinginvitation.service.WeddingGuestService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/guests")
class WeddingGuestController(
    private val weddingGuestService: WeddingGuestService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: WeddingGuestRequest): WeddingGuestResponse {
        return weddingGuestService.create(request)
    }
}