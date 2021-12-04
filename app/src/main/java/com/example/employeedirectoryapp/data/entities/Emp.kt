package com.example.employeedirectoryapp.data.entities

import androidx.room.*
import com.example.employeedirectoryapp.utils.CompanyConverter
import com.example.employeedirectoryapp.utils.Converter

@Entity(tableName = "employees")
data class Emp(
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "username")
    val username: String,
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "profile_image")
    val profile_image: String,
    @TypeConverters(Converter::class)
    @ColumnInfo(name = "address")
    val address: EmpAddress,
    @ColumnInfo(name = "phone")
    val phone: String,
    @ColumnInfo(name = "website")
    val website: String,
    @TypeConverters(CompanyConverter::class)
    @ColumnInfo(name = "company")
    val company: EmpCompany
)