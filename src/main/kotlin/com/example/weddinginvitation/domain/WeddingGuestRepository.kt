package com.example.weddinginvitation.domain

import org.springframework.data.jpa.repository.JpaRepository

interface WeddingGuestRepository : JpaRepository<WeddingGuest, Long> {}
