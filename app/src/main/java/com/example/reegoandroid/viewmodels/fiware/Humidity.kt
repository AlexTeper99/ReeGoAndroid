package com.example.retrofit_demo.fiware
import com.example.reegoandroid.viewmodels.fiware.MetadataX
import com.squareup.moshi.Json

data class Humidity(
    val metadata: MetadataX,
    @Json(name = "type")
    val type: String,
    @Json(name = "value")
    val value: String
)