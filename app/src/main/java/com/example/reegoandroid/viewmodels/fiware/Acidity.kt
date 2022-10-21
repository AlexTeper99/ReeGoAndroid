package com.example.reegoandroid.viewmodels.fiware
import com.squareup.moshi.Json

data class Acidity(
    
    val metadata: MetadataX,
    @Json(name = "type")
    val type: String,
    @Json(name = "value")
    val value: String
)