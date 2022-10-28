package com.example.reegoandroid.viewmodels.node

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

interface NodeApi {

    // Using MOCK API
    companion object {
        val instance: NodeApi? = Retrofit
            .Builder()
            .baseUrl("http://192.168.0.39/")
        //.baseUrl("https://mocki.io/v1/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()
            .create(NodeApi::class.java)
    }

    // CLIMATE ENDPIONT
    @GET("3acae5a5-31f1-45bb-9c89-ef602ce19d35")
    suspend fun getClimateData(
        // @Header("Fiware-Service") fiwareService: String="sensor",
        // @Header("Fiware-ServicePath") fiwareServicePath: String="/",
        @Header("Accept") fiwareServicePath: String= "application/json",
        // @Body(objeto) - envia al endpoint el objeto directamente - lo convierte a json

    ): ClimateData

    // User List Endpoint
    @GET("users")
    suspend fun getUserList(
        @Header("Accept") fiwareServicePath: String= "application/json",
    ): MutableList<UserData>
}