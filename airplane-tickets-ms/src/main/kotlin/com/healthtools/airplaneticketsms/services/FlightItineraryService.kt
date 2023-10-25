package com.healthtools.airplaneticketsms.services

import com.healthtools.airplaneticketsms.entities.FlightItinerary
import com.healthtools.airplaneticketsms.exceptions.NotFoundException
import com.healthtools.airplaneticketsms.repositories.FlightItineraryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class FlightItineraryService {

    @Autowired
    private lateinit var flightItineraryRepository: FlightItineraryRepository

    fun findItineraries(
        departureAirportId: Long,
        arrivalAirportId: Long,
        departureDate: LocalDate
    ): List<FlightItinerary> {
        val startOfDay = departureDate.atStartOfDay()
        val endOfDay = departureDate.plusDays(1).atStartOfDay()

        return flightItineraryRepository.findItinerariesByDepartureDay(
            departureAirportId,
            arrivalAirportId,
            startOfDay,
            endOfDay
        )
    }

    fun findByOrThrowException(id: Long): FlightItinerary {
        return flightItineraryRepository.findByIdOrNull(id) ?: throw NotFoundException("Flight itinerary not found")
    }
}