package com.example.dtabookstore.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.dtabookstore.R
import com.example.dtabookstore.data.DataSourceBooks
import com.example.dtabookstore.data.DataSourceNotification
import com.example.dtabookstore.databinding.ActivityMainBinding
import com.example.dtabookstore.network.Api
import com.example.dtabookstore.network.Book
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    // tao bien tham chieu view binding
    lateinit var binding: ActivityMainBinding

    // tao bien tham chieu navcontroller
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // lay navController cua navHostFragment
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // set navcontroller cho bottom navigation
        binding.bottomNavigation.setupWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


}