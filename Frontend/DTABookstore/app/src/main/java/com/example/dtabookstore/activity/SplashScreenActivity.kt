package com.example.dtabookstore.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.dtabookstore.R

class SplashScreenActivity : AppCompatActivity() {

    lateinit var handler: Handler;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash_screen)

        handler = Handler()
        handler.postDelayed({

            val intent = Intent(this, LogInSignUpActivity::class.java)
            startActivity(intent)
            finish()
        }, 1000) // delaying 3 seconds to open MainActivity
    }
}
