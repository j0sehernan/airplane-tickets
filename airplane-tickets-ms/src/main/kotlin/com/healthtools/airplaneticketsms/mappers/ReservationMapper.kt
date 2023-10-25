package com.healthtools.airplaneticketsms.mappers

import com.healthtools.airplaneticketsms.dtos.requests.ReservationRequest
import com.healthtools.airplaneticketsms.entities.Customer
import com.healthtools.airplaneticketsms.entities.FlightItinerary
import com.healthtools.airplaneticketsms.entities.Reservation

fun ReservationRequest.toEntity(customer: Customer, flightItinerary: FlightItinerary) = Reservation(
    customer = customer,
    flightItinerary = flightItinerary,
    price = flightItinerary.price,
)