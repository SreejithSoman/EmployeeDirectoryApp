package com.example.employeedirectoryapp.ui.Emps

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.employeedirectoryapp.data.repository.EmpRepository

class EmpsViewModel @ViewModelInject constructor(
    private val repository: EmpRepository
) : ViewModel() {

    val emps = repository.getEmps()
}