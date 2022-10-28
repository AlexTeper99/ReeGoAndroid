package com.example.reegoandroid.viewmodels.fiware

import com.squareup.moshi.Json

data class PlotData(
    @Json(name = "desc")
    val desc: String,
    @Json(name = "humedadMax")
    val humedadMax: Int,
    @Json(name = "idCrop")
    val idCrop: Int,
    @Json(name = "idPlot")
    val idPlot: Int,
    @Json(name="phMinimo")    
    val phMinimo: Int,
    @Json(name = "tempMinima")
    val tempMinima: Int,
    @Json(name = "tipoCultivo")
    val tipocultivo: String
)