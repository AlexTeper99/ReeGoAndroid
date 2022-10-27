package com.example.reegoandroid.viewmodels.fiware

import com.squareup.moshi.Json

data class SensorRequestBody(
    @Json(name = "entityId")
    val entityId: String,
    @Json(name = "serviceHeader")
    val serviceHeader: String,
    @Json(name = "servicePathHeader")
    val servicePathHeader: String,
)