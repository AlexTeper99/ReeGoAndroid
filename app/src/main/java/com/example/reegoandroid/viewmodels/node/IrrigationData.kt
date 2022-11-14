package com.example.reegoandroid.viewmodels.node

import com.squareup.moshi.Json

data class IrrigationData(
    @Json(name = "createdAt")
    val createdAt: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "idPlot")
    val idPlot: Int,
    @Json(name = "updatedAt")
    val updatedAt: String,
    @Json(name = "waterUsed")
    val waterUsed: Int
)