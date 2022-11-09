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
            .baseUrl("http://192.168.0.160/")
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
    @GET("irrigations/{idPlot}") //  irrigations/Idplot
    suspend fun getIrrigationList(
        @Path("idPlot") id: Int,
    ) : List<IrrigationData>

    // Get Comment List of a give Irrigation id
    @GET("comments/{id}")
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

    @POST("user")
    suspend fun createUser(
        @Query("name") userName: String,
        @Query("email") userEmail: String,
        @Query("password") userPass: String,
        @Query("plotId") userPlotId: Int,
        @Query("isAdmin") userIsadmin: Boolean,
    ) : String

    @PUT("user")
    suspend fun updateUser(
        @Query("userId") userId: Int,
        @Query("name") userName: String,
        @Query("email") userEmail: String,
        @Query("password") userPass: String,
        @Query("plotId") userPlotId: Int,
        @Query("isAdmin") userIsadmin: Boolean,
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

}