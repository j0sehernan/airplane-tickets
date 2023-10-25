package com.healthtools.airplaneticketsms.services

import com.healthtools.airplaneticketsms.dtos.requests.ReservationRequest
import com.healthtools.airplaneticketsms.entities.Reservation
import com.healthtools.airplaneticketsms.mappers.toEntity
import com.healthtools.airplaneticketsms.repositories.ReservationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ReservationService {

    @Autowired
    private lateinit var reservationRepository: ReservationRepository

    @Autowired
    private lateinit var customerService: CustomerService

    @Autowired
    private lateinit var flightItineraryService: FlightItineraryService

    fun findByCustomerEmail(email: String): List<Reservation> {
        return reservationRepository.findByCustomerEmail(email)
    }

    fun save(reservationRequest: ReservationRequest): Reservation {
        val customer = customerService.getOrCreate(reservationRequest.customer)
        val flightItinerary = flightItineraryService.findByOrThrowException(reservationRequest.flightItineraryId)
        return reservationRepository.save(reservationRequest.toEntity(customer, flightItinerary))
    }
}

