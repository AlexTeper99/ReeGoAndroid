package com.example.reegoandroid.viewmodels.node

import com.squareup.moshi.Json

data class BackofficeData(
    @Json(name = "cantMaiz")
    val cantMaiz: Int,
    @Json(name = "cantRiegosRealizados")
    val cantRiegosRealizados: Int,
    @Json(name = "cantSoja")
    val cantSoja: Int,
    @Json(name = "cantTrigo")
    val cantTrigo: Int,
    @Json(name = "cantplots")
    val cantplots: Int
)