package com.example.employeedirectoryapp.data.entities

data class EmpAddress(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: EmpGeo
)
