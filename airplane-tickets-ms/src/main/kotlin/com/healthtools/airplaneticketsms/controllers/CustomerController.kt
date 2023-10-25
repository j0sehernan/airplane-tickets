package com.healthtools.airplaneticketsms.controllers

import com.healthtools.airplaneticketsms.entities.Customer
import com.healthtools.airplaneticketsms.services.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customers")
class CustomerController {
    @Autowired
    private lateinit var customerService: CustomerService

    @GetMapping
    fun getAllCustomers(): List<Customer> {
        return customerService.findAll()
    }

    @GetMapping("/by-email/{email}")
    fun findByEmail(@PathVariable email: String): Customer? {
        return customerService.findByEmail(email)
    }
}