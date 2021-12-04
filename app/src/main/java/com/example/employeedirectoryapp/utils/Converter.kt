package com.example.employeedirectoryapp.utils

import androidx.room.TypeConverter
import com.example.employeedirectoryapp.data.entities.EmpAddress
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

class Converter {
    @TypeConverter
    fun stringToList(data: String?): List<EmpAddress?>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object :
            TypeToken<List<EmpAddress?>?>() {}.type
        return Gson().fromJson<List<EmpAddress?>>(data, listType)
    }

    @TypeConverter
    fun listToString(someObjects: List<EmpAddress?>?): String? {
        return Gson().toJson(someObjects)
    }
}

