package com.example.dtabookstore.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dtabookstore.data.DataSourceUserDangNhap
import com.example.dtabookstore.network.Api
import com.example.dtabookstore.network.Book
import com.example.dtabookstore.network.UserDangNhapRespond
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class HomeViewModel : ViewModel() {
    // biến lưu danh sách các sách bán chạy nhất
    private val _danhSachSachBanChayNhat = MutableLiveData<List<Book>>()
    val danhSachSachBanChayNhat: LiveData<List<Book>> = _danhSachSachBanChayNhat

    // biến lưu danh sách các sách mới nhất
    private val _danhSachSachMoiNhat = MutableLiveData<List<Book>>()
    val danhSachSachMoiNhatt: LiveData<List<Book>> = _danhSachSachMoiNhat

//    private fun loadDanhSachSachBanChayNhat() {
//
//        try {
//
//            // call api lấy danh sách sách bán chạy nhất
//            Api.retrofitService.getDanhSachSach().enqueue(object : Callback<MutableList<Book>> {
//
//                override fun onResponse(call: Call<List<Book>>, response: Response<List<Book>>) {
//
//                    // lấy respond body
//                    val body = response.body()!!
//
//                    _danhSachSachBanChayNhat.value = body
//                }
//
//                override fun onFailure(call: Call<List<Book>>, t: Throwable) {
//                    Log.d("HomeFragment", "Lỗi mạng khi load danh sách sách bán chạy nhất!")
//                }
//            })
//
//        } catch (e: Exception) {
//            Log.d("HomeFragment", e.message.toString())
//        }
//    }
}