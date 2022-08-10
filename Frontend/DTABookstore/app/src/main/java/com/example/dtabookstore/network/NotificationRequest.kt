package com.example.dtabookstore.network

import com.squareup.moshi.Json

data class NotificationRequest(
    @Json(name = "userId") val userId: String?
)
