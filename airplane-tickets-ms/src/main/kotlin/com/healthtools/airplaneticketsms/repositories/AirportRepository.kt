package com.healthtools.airplaneticketsms.repositories

import com.healthtools.airplaneticketsms.entities.Airport
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AirportRepository : JpaRepository<Airport, Long> {

    fun findAllByOrderByNameAsc(): List<Airport>
    fun findByLocationIgnoreCaseContainingOrderByNameAsc(location: String): List<Airport>
}