package com.example.dtabookstore.network

import com.squareup.moshi.Json

data class OrderDetailRespond(
    @Json(name = "_id") val orderId: String?,
    @Json(name = "status") val status: String?,
    @Json(name = "date") val date: String?,
    @Json(name = "products") val products: List<Map<String, String>>?,
    @Json(name = "total") val total: String?,
)
