package com.example.weddinginvitation.service

import com.example.weddinginvitation.domain.WeddingGuest
import com.example.weddinginvitation.domain.WeddingGuestRepository
import com.example.weddinginvitation.dto.WeddingGuestRequest
import com.example.weddinginvitation.dto.WeddingGuestResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WeddingGuestService(
    private val weddingGuestRepository: WeddingGuestRepository
) {
    @Transactional
    fun create(request: WeddingGuestRequest): WeddingGuestResponse {
        val guest = WeddingGuest(
            name = request.name,
            phoneLastDigits = request.phoneLastDigits,
            side = request.side,
            willAttend = request.willAttend,
            message = request.message
        )
        val savedGuest = weddingGuestRepository.save(guest)
        return WeddingGuestResponse.from(savedGuest)
    }
}
