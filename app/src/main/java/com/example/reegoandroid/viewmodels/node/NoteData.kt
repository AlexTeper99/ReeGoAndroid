package com.example.reegoandroid.viewmodels.node

import com.squareup.moshi.Json

data class NoteData(
    @Json(name = "createdAt")
    val createdAt: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "idIrrigation")
    val idIrrigation: Int,
    @Json(name = "text")
    val text: String,
    @Json(name = "updatedAt")
    val updatedAt: String
)