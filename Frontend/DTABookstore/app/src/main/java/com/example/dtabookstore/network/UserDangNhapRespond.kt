package com.example.dtabookstore.network

import com.squareup.moshi.Json

data class UserDangNhapRespond(
//    @Json(name="status") val status: String?,
//    @Json(name="token") val token: String?,
    @Json(name="userId") val userId: String?,
    @Json(name="fullname") val fullName: String?,
    @Json(name="email") val email: String?,
    @Json(name="phone") val phone: String?,
)