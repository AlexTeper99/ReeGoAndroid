package com.example.reegoandroid.viewmodels.fiware
import com.example.retrofit_demo.fiware.Humidity
import com.squareup.moshi.Json

data class SensorData(
    val TimeInstant: TimeInstant,
    val acidity: Acidity,
    val humidity: Humidity,
    @Json(name = "id")
    val id: String,
    val refStore: RefStore,
    val temperature: Temperature,
    @Json(name = "type")
    val type: String
)