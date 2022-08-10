package com.example.dtabookstore.network

import com.squareup.moshi.Json

data class Book(
    // đổi tên thuộc tính lấy từ api định dạng json
    @Json(name = "_id") val id: String,
    @Json(name = "title") val title: String,
    @Json(name = "price") val price: Int,
    @Json(name = "type") val type: String,
    @Json(name = "description") val description: String,
    @Json(name = "imagesUrl") val imgSrc: List<String>,
    @Json(name = "detail") val detail: Map<String, String>,
    @Json(name = "banchay") val banchay: Boolean,
    @Json(name = "sanphammoi") val sanphammoi: Boolean
) {

}