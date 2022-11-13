package com.example.reegoandroid.viewmodels.fiware

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface FiwareApi {

    companion object {
        val instance: FiwareApi? = Retrofit
            .Builder()
            .baseUrl("https://proyectofinaldreamteam-production-1037.up.railway.app/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()
            .create(FiwareApi::class.java)
    }

    @GET("iot")
    suspend fun getSensorData(
        @Header("Content-Type") cType: String= "application/json",
        @Query("entityId") entityId: String = "ambiente:001",
        @Query("serviceHeader") serviceHeader: String = "sensor",
        @Query("servicePathHeader") servicePathHeader: String = "/"
    ): SensorData

    @GET("plot/{plotId}")
    suspend fun getPlotData(
        @Path("plotId") plotId: String,
        @Header("Content-Type") cType: String= "application/json",
    ): PlotData

}