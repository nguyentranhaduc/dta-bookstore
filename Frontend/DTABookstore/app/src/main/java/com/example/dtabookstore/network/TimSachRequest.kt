package com.example.dtabookstore.network

import com.squareup.moshi.Json

data class TimSachRequest(
    @Json(name = "tuKhoa") val tuKhoa: String?
)
