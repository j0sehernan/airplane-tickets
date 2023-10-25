package com.healthtools.airplaneticketsms.controllers

import com.healthtools.airplaneticketsms.entities.Airport
import com.healthtools.airplaneticketsms.services.AirportService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/airports")
class AirportController {
    @Autowired
    private lateinit var airportService: AirportService

    @GetMapping
    fun findAllByOrderByNameAsc(@RequestParam(required = false) location: String?): List<Airport> {
        return airportService.findAllByOrderByNameAsc(location)
    }
}