package com.healthtools.airplaneticketsms.services

import com.healthtools.airplaneticketsms.dtos.requests.ReservationRequest.CustomerRequest
import com.healthtools.airplaneticketsms.entities.Customer
import com.healthtools.airplaneticketsms.mappers.toEntity
import com.healthtools.airplaneticketsms.repositories.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CustomerService {

    @Autowired
    private lateinit var customerRepository: CustomerRepository

    fun findAll(): List<Customer> {
        return customerRepository.findAll()
    }

    fun findByEmail(email: String): Customer? {
        return customerRepository.findByEmail(email)
    }

    fun getOrCreate(customerRequest: CustomerRequest): Customer {
        return findByEmail(customerRequest.email) ?: customerRepository.save(customerRequest.toEntity())
    }
}