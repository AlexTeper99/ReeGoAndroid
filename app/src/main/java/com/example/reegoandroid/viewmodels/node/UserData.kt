package com.example.reegoandroid.viewmodels.node

import com.squareup.moshi.Json

data class UserData(
    @Json(name = "createdAt")
    val createdAt: String,
    @Json(name = "email")
    val email: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "idPlot")
    val idPlot: Int,
    @Json(name = "isAdmin")
    val isAdmin: Boolean,
    @Json(name = "name")
    val name: String,
    @Json(name = "password")
    val password: String,
    @Json(name = "updatedAt")
    val updatedAt: String
)