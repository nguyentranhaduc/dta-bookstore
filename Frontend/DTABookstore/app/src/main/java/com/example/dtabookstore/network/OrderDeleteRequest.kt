package com.example.dtabookstore.network

import com.squareup.moshi.Json

data class OrderDeleteRequest(
    @Json(name = "orderId") val orderId: String?,
    @Json(name = "userId") val userId: String?
)
