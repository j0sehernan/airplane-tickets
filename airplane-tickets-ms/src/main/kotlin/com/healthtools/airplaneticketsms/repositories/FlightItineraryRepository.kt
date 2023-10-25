package com.healthtools.airplaneticketsms.repositories

import com.healthtools.airplaneticketsms.entities.FlightItinerary
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface FlightItineraryRepository : JpaRepository<FlightItinerary, Long> {

    @Query("SELECT f FROM FlightItinerary f WHERE f.departureAirport.id = :departureAirportId AND f.arrivalAirport.id = :arrivalAirportId AND f.departureTime >= :startOfDay AND f.departureTime < :endOfDay")
    fun findItinerariesByDepartureDay(
        @Param("departureAirportId") departureAirportId: Long,
        @Param("arrivalAirportId") arrivalAirportId: Long,
        @Param("startOfDay") startOfDay: LocalDateTime,
        @Param("endOfDay") endOfDay: LocalDateTime
    ): List<FlightItinerary>
}