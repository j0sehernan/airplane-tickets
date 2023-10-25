package com.healthtools.airplaneticketsms.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "airports")
data class Airport(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(nullable = false, length = 200)
    val name: String,

    @Column(length = 200)
    val location: String? = null,

    @Column(length = 100)
    val code: String?,
)