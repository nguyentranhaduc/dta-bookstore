package com.example.dtabookstore.network

import com.squareup.moshi.Json

data class DonHang (
    @Json(name = "userId") val userId: String,
    @Json(name = "danhSachSanPhamVaSoLuong") val danhSachSanPhamVaSoLuong: MutableList<MutableMap<String, String>>,
    @Json(name = "total") val total: Int
)