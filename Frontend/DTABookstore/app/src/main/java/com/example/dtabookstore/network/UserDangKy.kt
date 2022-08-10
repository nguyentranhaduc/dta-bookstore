package com.example.dtabookstore.network

import com.squareup.moshi.Json

data class UserDangKy(
    @Json(name="fullname") val fullname: String?,
    @Json(name="email") val email: String?,
    @Json(name="phone") val phone: String?,
    @Json(name="password") val password: String?
)
