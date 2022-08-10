package com.example.dtabookstore.network

import com.squareup.moshi.Json

data class UserProfileRespond(
    @Json(name="fullname") val fullName: String?,
    @Json(name="email") val email: String?,
    @Json(name="phone") val phone: String?,
    @Json(name="address") val address: String?
)
