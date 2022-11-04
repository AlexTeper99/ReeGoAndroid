package com.example.reegoandroid.viewmodels.node

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface NodeApi {

    // Using Local Host
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
    @GET("weatherInfo/buenosaires")
    suspend fun getClimateData(
        // @Header("Fiware-Service") fiwareService: String="sensor",
        // @Header("Fiware-ServicePath") fiwareServicePath: String="/",
//        @Header("Accept") fiwareServicePath: String= "application/json",
        // @Body(objeto) - envia al endpoint el objeto directamente - lo convierte a json

    ): ClimateData

    // Get Irrigation List
    @GET("irrigations/1")
    suspend fun getIrrigationList() : List<IrrigationData>

    // Get Comment List of a give Irrigation id
    @GET("irrigation/{id}/comments")
    //@GET("irrigation/1/comments")
    suspend fun getIrrigationComments(
        @Path("id") id: Int,
    ) : List<NoteData>

    // User List Endpoint
    @GET("users")
    suspend fun getUserList(
        @Header("Accept") fiwareServicePath: String= "application/json",
    ): MutableList<UserData>

    // Update Comment
    @PUT("comment/{id}")
    suspend fun updateComment(
        @Path("id") commentId: Int,
        @Query("comment") commentText: String,
    ) : String
}