package com.example.dtabookstore.network

import com.squareup.moshi.Json

data class NotificationRespond(
    @Json(name = "title") val title: String?,
    @Json(name = "date") val date: String?,
    @Json(name = "content") val content: String?
)
