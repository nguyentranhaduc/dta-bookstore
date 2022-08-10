package com.example.dtabookstore.network

import android.text.Editable
import com.squareup.moshi.Json

data class UserUpdateInformation(
    @Json(name="userId") val userId: String?,
    @Json(name="fullname") val fullName: String?,
    @Json(name="phone") val phone: String?,
    @Json(name="email") val email: String?,
    @Json(name="address") val address: String?,
)