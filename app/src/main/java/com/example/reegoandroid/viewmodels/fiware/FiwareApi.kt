package com.example.reegoandroid.viewmodels.fiware

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

interface FiwareApi {

    companion object {
        val instance: FiwareApi? = Retrofit
            .Builder()
            .baseUrl("http://192.168.0.39/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()
            .create(FiwareApi::class.java)
    }

    @GET("iot")
    suspend fun getSensorData(
//        @Header("Fiware-Service") fiwareService: String="sensor",
//        @Header("Fiware-ServicePath") fiwareServicePath: String="/",
        @Header("Accept") fiwareServicePath: String= "application/json",

    ): SensorData
}