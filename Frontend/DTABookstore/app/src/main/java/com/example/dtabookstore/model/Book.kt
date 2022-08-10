package com.example.dtabookstore.model

data class Book(
    val book_id: String,
    val book_image: List<Int>,
    val book_title: String,
    val book_content: String,
    val book_price: Int,
    val book_detail: Map<String, String>,
    val book_category: String,
    val ban_chay: Boolean,
    val sach_moi: Boolean
)