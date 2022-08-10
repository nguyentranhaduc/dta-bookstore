package com.example.dtabookstore.network

import com.squareup.moshi.Json

data class TimSachRespond(
    @Json(name = "_id") val productId: String?,
    @Json(name = "title") val title: String?
)
