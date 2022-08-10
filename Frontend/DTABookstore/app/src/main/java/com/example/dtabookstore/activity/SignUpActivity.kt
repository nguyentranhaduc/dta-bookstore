package com.example.dtabookstore.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.dtabookstore.R
import com.example.dtabookstore.data.DataSourceGioHang
import com.example.dtabookstore.data.DataSourceOrder
import com.example.dtabookstore.databinding.ActivitySignUpBinding
import com.example.dtabookstore.network.Api
import com.example.dtabookstore.network.UserDangKy
import com.example.dtabookstore.network.UserDangKyRespond
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.MainScope
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class SignUpActivity : AppCompatActivity() {

    // tạo biến tham chiếu view binding
    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // set sự kiện nhấp cho nút đăng ký
        binding.dangKyButton.setOnClickListener {
            // lấy các thông tin từ editText
            val fullName = binding.hoTenEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val phone = binding.phoneEditText.text.toString()

            val password = binding.passwordEditText.text.toString()
            val passwordXacNhan = binding.passwordConfirmEditText.text.toString()

            if (password == passwordXacNhan) {

                // tạo một đối tượng mới để đưa vào api post lên server
                val user = UserDangKy(fullName, email, phone, password)

                try {

                    // call api đăng ký tài khoản
                    Api.retrofitService.dangKy(user).enqueue(object: Callback<UserDangKyRespond> {

                        // hàm xử lý khi call api thành công
                        override fun onResponse(call: Call<UserDangKyRespond>, response: Response<UserDangKyRespond>) {

                            val body = response.body()!!

                            Snackbar.make(binding.dangKyButton, body?.status.toString(), Snackbar.LENGTH_LONG).show()
                        }

                        // hàm xử lý khi call api thất bại
                        override fun onFailure(call: Call<UserDangKyRespond>, t: Throwable) {
                            Snackbar.make(binding.dangKyButton, "Lỗi mạng!", Snackbar.LENGTH_LONG).show()
                        }
                    })

                } catch (e: Exception) {
                    Log.d("SignUpActivity", e.message.toString())
                }

            } else {

                MaterialAlertDialogBuilder(this)
                    .setTitle("Lỗi")
                    .setMessage("Nhập lại mật khẩu chưa chính xác, mời thử lại!")
                    .setPositiveButton("Đồng ý") { dialog, which ->
                        // Respond to positive button press

                    }
                    .show()

            }
        }
    }
}