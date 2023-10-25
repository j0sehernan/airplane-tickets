package com.healthtools.airplaneticketsms.mappers

import com.healthtools.airplaneticketsms.dtos.requests.ReservationRequest.CustomerRequest
import com.healthtools.airplaneticketsms.entities.Customer

fun CustomerRequest.toEntity() = Customer(
    id = 0,
    fullName = fullName,
    email = email,
    phone = phone
)