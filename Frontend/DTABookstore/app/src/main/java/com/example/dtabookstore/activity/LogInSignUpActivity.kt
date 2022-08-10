package com.example.dtabookstore.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dtabookstore.R
import com.example.dtabookstore.databinding.ActivityLogInSignUpBinding

class LogInSignUpActivity : AppCompatActivity() {

    lateinit var binding: ActivityLogInSignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in_sign_up)

        binding = ActivityLogInSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signinButton.setOnClickListener{

            val intent = Intent(this, SignInActivity::class.java).apply {
//                putExtra(EXTRA_MESSAGE, message)
            }
            startActivity(intent)
        }

        binding.signupButton.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java).apply {
//                putExtra(EXTRA_MESSAGE, message)
            }
            startActivity(intent)
        }
    }
}