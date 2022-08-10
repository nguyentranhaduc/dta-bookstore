package com.example.dtabookstore.data

import com.example.dtabookstore.network.Book

object DataSourceBooks {
    // danh sách Sách
    var books: MutableList<Book> = mutableListOf()

    //  Hàm tìm danh sách sách mới nhất
    fun findBookMoiNhat(): List<Book> {
        val listBook: MutableList<Book> = mutableListOf()

        DataSourceBooks.books.forEach {
            if (it.sanphammoi) {
                listBook.add(it)
            }
        }

        return listBook
    }

    //  Hàm tìm danh sách sách bán chạy
    fun findBookBanChay(): List<Book> {
        val listBook: MutableList<Book> = mutableListOf()

        DataSourceBooks.books.forEach {
            if (it.banchay) {
                listBook.add(it)
            }
        }

        return listBook
    }

    // Hàm lấy danh sách sách theo thể loại
    fun findSpecificCategoryBooks(inputCategory: String): List<Book> {
        val listBook: MutableList<Book> = mutableListOf()

        DataSourceBooks.books.forEach {
            if (it.type == inputCategory) {
                listBook.add(it)
            }
        }

        return listBook
    }

    // Hàm tìm sách theo id trong dataSource
    fun findABook(inputBookId: String): Book? {
        DataSourceBooks.books.forEach {
            if (it.id == inputBookId) {
                return it
            }
        }

        return null
    }

    // Hàm tìm tên sách từ id của sách
    fun getBookTitle(inputBookId: String): String {
        DataSourceBooks.books.forEach {
            if (it.id == inputBookId) {
                return it.title
            }
        }

        return "Lỗi!"
    }
}