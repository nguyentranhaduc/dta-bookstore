package com.example.dtabookstore.data

import android.util.Log

object DataSourceGioHang {
    val books: MutableList<MutableMap<String, String>> = mutableListOf(
//        mapOf(
//            "book_id" to "1",
//            "so_luong" to "9"
//        )
    )

    var tongTien: Int = 0

    fun findABook(bookIdInput: String): Boolean {
        books.forEach{
            if (it["productId"] == bookIdInput) {
                return true
            }
        }
        return false
    }

    fun updateQuantityOfABook(bookIdInput: String, quantityToAdd: Int) {
        books.forEach{
            if (it["productId"] == bookIdInput) {
                var soLuongCu: Int? = it["quantity"]?.toInt()
                if (soLuongCu != null) {
//                    Log.d("DataSourceGioHang", "so luong cu khac null, bang ${soLuongCu}")
                    soLuongCu += quantityToAdd
//                    Log.d("DataSourceGioHang", "so luong cu sau khi cong: ${soLuongCu}")
//                    it["so_luong"] to soLuongCu.toString()
                    it.put("quantity", soLuongCu.toString())
//                    Log.d("DataSourceGioHang", "so luong của sách sau khi đã cập nhật: ${it["so_luong"]}")

                    // cập nhật tổng tiền giỏ hàng
                    val theBook = DataSourceBooks.findABook(bookIdInput)
                    DataSourceGioHang.tongTien += theBook!!.price * quantityToAdd
                }
            }
        }
    }
}