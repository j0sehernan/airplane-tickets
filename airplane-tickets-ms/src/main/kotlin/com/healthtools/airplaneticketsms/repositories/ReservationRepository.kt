package com.healthtools.airplaneticketsms.repositories

import com.healthtools.airplaneticketsms.entities.Reservation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReservationRepository : JpaRepository<Reservation, Long> {
    fun findByCustomerEmail(email: String): List<Reservation>
}