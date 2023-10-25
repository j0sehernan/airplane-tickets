package com.healthtools.airplaneticketsms.controllers

import com.healthtools.airplaneticketsms.dtos.requests.ReservationRequest
import com.healthtools.airplaneticketsms.services.ReservationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/reservations")
class ReservationController {
    @Autowired
    private lateinit var reservationService: ReservationService

    @GetMapping("/by-customer-email/{email}")
    fun findByCustomerEmail(@PathVariable email: String) = reservationService.findByCustomerEmail(email)

    @PostMapping
    fun save(@RequestBody reservationRequest: ReservationRequest) = reservationService.save(reservationRequest)

}