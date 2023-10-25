package com.healthtools.airplaneticketsms.dtos.requests

data class ReservationRequest(
    val customer: CustomerRequest,
    val flightItineraryId: Long,
) {
    data class CustomerRequest(
        val email: String,
        val fullName: String,
        val phone: String,
    )
}
