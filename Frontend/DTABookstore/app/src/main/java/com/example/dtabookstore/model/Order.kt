package com.example.dtabookstore.model

class Order(
    val maDonHang: String,
    val trangThaiGiaoHang: String,
    val tongTien: Int,
    val books: MutableList<Map<String, String>>
)