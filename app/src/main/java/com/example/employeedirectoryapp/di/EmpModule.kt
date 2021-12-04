package com.example.employeedirectoryapp.di

import android.content.Context
import com.example.employeedirectoryapp.data.local.AppDatabase
import com.example.employeedirectoryapp.data.local.EmpDao
import com.example.employeedirectoryapp.data.remote.EmpRemoteDataSource
import com.example.employeedirectoryapp.data.remote.EmpService
import com.example.employeedirectoryapp.data.repository.EmpRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object EmpModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("http://www.mocky.io/v2/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideEmpService(retrofit: Retrofit): EmpService = retrofit.create(EmpService::class.java)

    @Singleton
    @Provides
    fun provideEmpRemoteDataSource(empService: EmpService) = EmpRemoteDataSource(empService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideEmpDao(db: AppDatabase) = db.empDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: EmpRemoteDataSource,
                          localDataSource: EmpDao
    ) = EmpRepository(remoteDataSource, localDataSource)
}