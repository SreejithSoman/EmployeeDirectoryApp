package com.example.employeedirectoryapp.data.remote

import com.example.employeedirectoryapp.data.entities.Emp
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EmpService {
    @GET("5d565297300000680030a986")
    suspend fun getAllEmps() : Response<List<Emp>>

//    suspend fun getEmp(@Path("id") id: Int): Response<Emp>
}