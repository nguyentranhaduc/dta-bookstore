package com.example.dtabookstore.network

import com.squareup.moshi.Json

data class DonHangRespond (
    @Json(name = "status") val status: String
)