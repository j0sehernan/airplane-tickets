package com.healthtools.airplaneticketsms.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "customers")
data class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "email", length = 100)
    var email: String,

    @Column(name = "full_name", nullable = false, length = 200)
    var fullName: String = "",

    @Column(name = "phone", length = 20)
    var phone: String? = null
)