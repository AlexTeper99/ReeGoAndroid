package com.example.reegoandroid.viewmodels.fiware

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

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

    // example post some data via SensorRequestBody
//    @POST("iot")
//    suspend fun getSensorData(
//        @Header("Content-Type") cType: String= "application/json",
//        @Body reqBody: SensorRequestBody = SensorRequestBody(
//                                           "ambiente:001",
//                                           "sensor",
//                                           "/")
//        ): SensorData

    @GET("iot")
    suspend fun getSensorData(
        @Header("Content-Type") cType: String= "application/json",
        @Query("entityId") entityId: String = "ambiente:001",
        @Query("serviceHeader") serviceHeader: String = "sensor",
        @Query("servicePathHeader") servicePathHeader: String = "/"
    ): SensorData


}