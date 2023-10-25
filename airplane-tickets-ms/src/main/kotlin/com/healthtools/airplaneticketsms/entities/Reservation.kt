package com.healthtools.airplaneticketsms.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "reservations")
data class Reservation(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    val customer: Customer,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itinerary_id", referencedColumnName = "id")
    val flightItinerary: FlightItinerary,

    @Column(name = "status")
    val status: String = "RESERVED",

    @Column(name = "date")
    val date: LocalDateTime = LocalDateTime.now(),

    @Column(name = "price")
    val price: Double,
)
