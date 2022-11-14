package com.example.reegoandroid.viewmodels.node

import com.squareup.moshi.Json

data class ClimateData(
    @Json(name = "atmospheric_contition")
    val atmospheric_contition: String,
    @Json(name = "humidity")
    val humidity: Float,
    @Json(name = "location")
    val location: String,
    @Json(name = "rain_desc")
    val rain_desc: String,
    @Json(name = "raining")
    val raining: Boolean,
    @Json(name="temperature")
    val temperature: Float,
    @Json(name = "time")
    val time: String
)