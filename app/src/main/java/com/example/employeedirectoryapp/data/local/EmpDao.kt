package com.example.employeedirectoryapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.employeedirectoryapp.data.entities.Emp

@Dao
interface EmpDao {

    @Query("SELECT * FROM employees")
    fun getAllEmp() : LiveData<List<Emp>>

    @Query("SELECT * FROM employees WHERE id = :id")
    fun getEmp(id: Int): LiveData<Emp>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(emp: List<Emp>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(emp: Emp)


}