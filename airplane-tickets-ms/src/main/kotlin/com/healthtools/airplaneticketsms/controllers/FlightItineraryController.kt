package com.healthtools.airplaneticketsms.controllers

import com.healthtools.airplaneticketsms.entities.FlightItinerary
import com.healthtools.airplaneticketsms.services.FlightItineraryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/flight-itineraries")
class FlightItineraryController {
    @Autowired
    private lateinit var flightItineraryService: FlightItineraryService

    @GetMapping
    fun findItinerariesByDepartureDay(
        @RequestParam departureAirportId: Long,
        @RequestParam arrivalAirportId: Long,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) departureDate: LocalDate
    ): List<FlightItinerary> {
        return flightItineraryService.findItineraries(departureAirportId, arrivalAirportId, departureDate)
    }
}