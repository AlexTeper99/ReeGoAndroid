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
            .baseUrl("https://proyectofinaldreamteam-alan-test.up.railway.app/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()
            .create(NodeApi::class.java)
    }

    // Get climate data from weather api
    @GET("weatherInfo/{cityString}")
    suspend fun getClimateData(
        @Path("cityString") city: String,
    ): ClimateData

    // Get Irrigation List
    @GET("irrigations/{idPlot}") //  irrigations/Idplot
    suspend fun getIrrigationList(
        @Path("idPlot") id: Int,
    ) : List<IrrigationData>

    // Get Comment List of a give Irrigation id
    @GET("comments/{id}")
    suspend fun getIrrigationComments(
        @Path("id") id: Int,
    ) : List<NoteData>

    // Update Comment
    @PUT("comment/{id}")
    suspend fun updateComment(
        @Path("id") commentId: Int,
        @Query("comment") commentText: String,
    ) : String

    // delete Comment
    @DELETE("comment/{id}")
    suspend fun deleteComment(
        @Path("id") commentId: Int,
    ) : String

    // create Comment
    @POST("comment")
    suspend fun createComment(
        @Query("text") commentText: String,
        @Query("idIrrigation") irrigationId: Int,
    ) : String

    // Get All Users, With Aditional Info
    @GET("users/info")
    suspend fun getUserList(
        @Header("Accept") fiwareServicePath: String= "application/json",
    ): MutableList<UserData>

    @POST("user/plot")
    suspend fun createUser(
        @Query("name") userName: String,
        @Query("email") userEmail: String,
        @Query("password") userPass: String,
        @Query("isAdmin") userIsadmin: Boolean,
        @Query("plotCity") plotCity: String,
        @Query("plotDescription") plotDesc: String,
        @Query("cropType") cropType: String,
    ) : String

    @PUT("user/plot")
    suspend fun updateUser(
        @Query("userId") userId: Int,
        @Query("name") userName: String,
        @Query("email") userEmail: String,
        @Query("password") userPass: String,
        @Query("isAdmin") userIsadmin: Boolean,
        @Query("plotCity") plotCity: String,
        @Query("plotDescription") plotDesc: String,
        @Query("cropType") cropType: String,
    ) : String

    // Update Comment
    @DELETE("user/{id}")
    suspend fun deleteUser(
        @Path("id") userId: Int,
    ) : String

    @POST("user/login")
    suspend fun loginUser(
        @Query("email") email: String,
        @Query("password") password: String,
    ) : LoginData

    @GET("stats")
    suspend fun getBackofficeData() : BackofficeData

}