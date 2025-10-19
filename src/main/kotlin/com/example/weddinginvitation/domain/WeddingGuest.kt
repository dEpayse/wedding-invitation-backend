package com.example.weddinginvitation.domain

import com.example.weddinginvitation.domain.enums.GuestSide
import jakarta.persistence.*

@Entity
@Table(name = "wedding_guests")
class WeddingGuest(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var phoneLastDigits: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var side: GuestSide,

    @Column(nullable = false)
    var willAttend: Boolean = false,

    @Column(length = 1000)
    var message: String? = null

) : BaseEntity()
