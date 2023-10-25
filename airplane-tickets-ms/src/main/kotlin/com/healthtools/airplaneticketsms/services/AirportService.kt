package com.healthtools.airplaneticketsms.services

import com.healthtools.airplaneticketsms.entities.Airport
import com.healthtools.airplaneticketsms.repositories.AirportRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AirportService {

    @Autowired
    private lateinit var airportRepository: AirportRepository

    fun findAllByOrderByNameAsc(location: String?): List<Airport> {
        return location?.let {
            airportRepository.findByLocationIgnoreCaseContainingOrderByNameAsc(it)
        } ?: airportRepository.findAllByOrderByNameAsc()
    }
}