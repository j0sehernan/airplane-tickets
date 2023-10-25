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
@Table(name = "flight_itineraries")
data class FlightItinerary(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departure_airport_id", referencedColumnName = "id")
    var departureAirport: Airport,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arrival_airport_id", referencedColumnName = "id")
    var arrivalAirport: Airport,

    @Column(name = "departure_time")
    var departureTime: LocalDateTime?,

    @Column(name = "arrival_time")
    var arrivalTime: LocalDateTime?,

    @Column(name = "price")
    val price: Double,
)
