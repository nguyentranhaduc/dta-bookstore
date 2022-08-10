package com.example.dtabookstore.network

import com.squareup.moshi.Json

data class OrderDetailRequest(
    @Json(name = "orderId") val orderId: String?
)
