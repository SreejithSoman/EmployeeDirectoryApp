package com.example.employeedirectoryapp.utils

import androidx.room.TypeConverter
import com.example.employeedirectoryapp.data.entities.EmpAddress
import com.example.employeedirectoryapp.data.entities.EmpCompany
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

class CompanyConverter {
    @TypeConverter
    fun stringToList(data: String?): List<EmpCompany?>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object :
            TypeToken<List<EmpCompany?>?>() {}.type
        return Gson().fromJson<List<EmpCompany?>>(data, listType)
    }

    @TypeConverter
    fun listToString(someObjects: List<EmpCompany?>?): String? {
        return Gson().toJson(someObjects)
    }
}

