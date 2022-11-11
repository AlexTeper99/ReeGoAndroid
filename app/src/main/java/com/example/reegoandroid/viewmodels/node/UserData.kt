package com.example.reegoandroid.viewmodels.node

import com.squareup.moshi.Json

data class UserData(
    @Json(name = "userId")
    val userId: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "email")
    val email: String,
    @Json(name = "password")
    val password: String,
    @Json(name = "isAdmin")
    val isAdmin: Boolean,
    @Json(name = "plotCity")
    val plotCity: String,
    @Json(name = "plotDescription")
    val plotDescription: String,
    @Json(name = "idCrop")
    val idCrop: Int,
    @Json(name = "cropType")
    val cropType: String,

)