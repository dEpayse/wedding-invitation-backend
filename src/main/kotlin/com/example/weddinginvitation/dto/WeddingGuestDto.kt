package com.example.weddinginvitation.dto

import com.example.weddinginvitation.domain.WeddingGuest
import com.example.weddinginvitation.domain.enums.GuestSide
import java.time.LocalDateTime

data class WeddingGuestRequest(
    val name: String,
    val phoneLastDigits: String,
    val side: GuestSide,
    val willAttend: Boolean,
    val message: String? = null
)

data class WeddingGuestResponse(
    val id: Long,
    val name: String,
    val phoneLastDigits: String,
    val side: GuestSide,
    val willAttend: Boolean,
    val message: String?,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?
) {
    companion object {
        fun from(guest: WeddingGuest): WeddingGuestResponse {
            return WeddingGuestResponse(
                id = guest.id!!,
                name = guest.name,
                phoneLastDigits = guest.phoneLastDigits,
                side = guest.side,
                willAttend = guest.willAttend,
                message = guest.message,
                createdAt = guest.createdAt,
                updatedAt = guest.updatedAt
            )
        }
    }
}
