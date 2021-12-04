package com.example.employeedirectoryapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.employeedirectoryapp.data.entities.Emp
import com.example.employeedirectoryapp.utils.CompanyConverter
import com.example.employeedirectoryapp.utils.Converter

@Database(entities = [Emp::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class, CompanyConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun empDao(): EmpDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "employees")
                .fallbackToDestructiveMigration()
                .build()
    }

}