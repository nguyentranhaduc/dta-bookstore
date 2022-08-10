package com.example.dtabookstore.network

import com.squareup.moshi.Json

data class OrderDeleteRespond(
    @Json(name = "status") val status: String
)
