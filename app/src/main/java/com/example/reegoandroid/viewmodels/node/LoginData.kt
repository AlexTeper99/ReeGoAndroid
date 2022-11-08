package com.example.reegoandroid.viewmodels.node

import com.squareup.moshi.Json

data class LoginData (

   @Json(name = "idUser")
    val idUser: Int,
    @Json(name = "idPlot")
    val idPlot: Int,
    @Json(name = "city")
    val city: String,
    @Json(name = "isAdmin")
    val isAdmin: Boolean,
)