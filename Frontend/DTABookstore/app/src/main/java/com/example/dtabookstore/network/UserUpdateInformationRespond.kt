package com.example.dtabookstore.network

import com.squareup.moshi.Json

data class UserUpdateInformationRespond(
    @Json(name="status") val status: String?,
)
