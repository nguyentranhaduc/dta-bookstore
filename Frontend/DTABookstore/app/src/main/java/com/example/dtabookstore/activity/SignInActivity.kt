package com.example.dtabookstore.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.UserHandle
import android.provider.ContactsContract
import android.util.Log
import com.example.dtabookstore.R
import com.example.dtabookstore.data.DataSourceUserDangNhap
import com.example.dtabookstore.databinding.ActivitySignInBinding
import com.example.dtabookstore.network.Api
import com.example.dtabookstore.network.UserDangNhap
import com.example.dtabookstore.network.UserDangNhapRespond
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class SignInActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // intent đến MainActivity:
        val intent = Intent(this, MainActivity::class.java).apply {
//                putExtra(EXTRA_MESSAGE, message)
        }


        binding.loginButton.setOnClickListener {

            // lấy thông tin đăng nhập từ editText
            val emailInput = binding.emailEditText.text.toString()
            val passwordInput = binding.passwordEditText.text.toString()

            // tạo một user đăng nhập mới
            val userLogin = UserDangNhap(emailInput, passwordInput)


            try {

                // call api đăng nhập
                Api.retrofitService.dangNhap(userLogin).enqueue(object : Callback<UserDangNhapRespond> {

                    // nếu call api thành công
                    override fun onResponse(call: Call<UserDangNhapRespond>, response: Response<UserDangNhapRespond>) {
                        val body = response.body()!!


                        if (!body.userId.isNullOrEmpty()) { // Nếu đăng nhập thành công
                            Log.d("SignInActivity", body.toString())

                            Snackbar.make(binding.loginButton, "Đăng nhập thành công!", Snackbar.LENGTH_LONG).show()

                            DataSourceUserDangNhap.userId = body.userId.toString()
                            DataSourceUserDangNhap.fullName = body.fullName.toString()
                            DataSourceUserDangNhap.email = body.email.toString()
                            DataSourceUserDangNhap.phone = body.phone.toString()

                            startActivity(intent)

                        } else { // Nếu đăng nhập thất bại
                            Log.d("SignInActivity", body.userId.toString())
                            Snackbar.make(binding.loginButton, "Email hoặc mật khẩu không đúng!", Snackbar.LENGTH_LONG).show()

                        }
                    }

                    // nếu call api thất bại
                    override fun onFailure(call: Call<UserDangNhapRespond>, t: Throwable) {
                        Snackbar.make(binding.loginButton, "Lỗi mạng!", Snackbar.LENGTH_LONG).show()
                    }
                })

            } catch (e: Exception) {
                Log.d("SignInActivity", e.message.toString())
            }
        }

    }


}