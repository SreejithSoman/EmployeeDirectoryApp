package com.example.employeedirectoryapp.data.remote

import javax.inject.Inject

class EmpRemoteDataSource @Inject constructor(
    private val empService: EmpService
): BaseDataSource() {

    suspend fun getEmps() = getResult { empService.getAllEmps() }
    suspend fun getEmp(id: Int) = getResult { empService.getEmp(id) }
}