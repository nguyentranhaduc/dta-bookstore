package com.example.dtabookstore.network

import com.squareup.moshi.Json

data class UserProfile(
    @Json(name="userId") val userId: String?
)
