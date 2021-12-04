package com.example.employeedirectoryapp.ui.EmpDetails

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.employeedirectoryapp.data.entities.Emp
import com.example.employeedirectoryapp.data.repository.EmpRepository
import com.example.employeedirectoryapp.utils.Resource

class EmpDetailViewModel @ViewModelInject constructor(
    private val repository: EmpRepository
) : ViewModel() {

    private val _id = MutableLiveData<Int>()

    private val _emp = _id.switchMap { id ->
        repository.getEmp(id)
    }
    val emp: LiveData<Resource<Emp>> = _emp


    fun start(id: Int) {
        _id.value = id
    }

}