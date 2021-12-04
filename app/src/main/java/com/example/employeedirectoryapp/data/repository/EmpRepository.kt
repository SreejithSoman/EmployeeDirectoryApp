package com.example.employeedirectoryapp.data.repository

import com.example.employeedirectoryapp.data.local.EmpDao
import com.example.employeedirectoryapp.data.remote.EmpRemoteDataSource
import com.example.employeedirectoryapp.utils.performGetOperation
import javax.inject.Inject

class EmpRepository @Inject constructor(
    private val remoteDataSource: EmpRemoteDataSource,
    private val localDataSource: EmpDao
) {

    fun getEmp(id: Int) = performGetOperation(
        databaseQuery = { localDataSource.getEmp(id) },
        networkCall = { remoteDataSource.getEmp(id) },
        saveCallResult = { localDataSource.insert(it) }
    )

    fun getEmps() = performGetOperation(
        databaseQuery = { localDataSource.getAllEmp() },
        networkCall = { remoteDataSource.getEmps() },
        saveCallResult = { localDataSource.insertAll(it) }
    )
}