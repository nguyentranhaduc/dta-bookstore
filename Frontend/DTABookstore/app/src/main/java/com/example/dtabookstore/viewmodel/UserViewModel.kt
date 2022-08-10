package com.example.dtabookstore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dtabookstore.network.UserDangKyRespond

class UserViewModel: ViewModel() {

    private val _userId = MutableLiveData<String>()
    val userId: LiveData<String> = _userId

    private val _fullName = MutableLiveData<String>()
    val fullName: LiveData<String> = _fullName

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _phone = MutableLiveData<String>()
    val phone: LiveData<String> = _phone

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _cart = MutableLiveData<MutableList<String>>()
    val cart: LiveData<MutableList<String>> = _cart

    /**
     * Call dangNhap() on init.
     */
//    init {
//        dangNhap()
//    }

//    private fun dangNhap(user: UserRespond) {
//        _userId.value = user.userId
//        _fullName.value = user.fullName
//        _email.value = user.email
//        _phone.value = user.phone
//        _password.value = user.password
//        _cart.value = user.cart

//        viewModelScope.launch{
//            try {
//                // User object trả về
//                val user: User = Api.retrofitService.dangNhap()
//
//                _userId.value = user.userId
//                _fullName.value = user.fullName
//                _email.value = user.email
//                _phone.value = user.phone
//                _password.value = user.password
//                _cart.value = user.cart
//
//            } catch (e: Exception) {
//
//            }
//
//        }
//    }
}