package com.example.dtabookstore.network

import com.squareup.moshi.Json

data class UserOrderRespond(
    @Json(name="_id") val orderId: String?,
    @Json(name="status") val status: String?,
    @Json(name="total") val total: Int?,
    @Json(name="date") val date: String?,
    @Json(name="products") val products: List<Map<String, String>>?,
)