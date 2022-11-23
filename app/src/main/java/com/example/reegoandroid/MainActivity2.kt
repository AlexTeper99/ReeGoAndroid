package com.example.reegoandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment

private lateinit var navHostFragment2 : NavHostFragment

private var isLoggedIn = false

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        navHostFragment2 = supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment

    }

    // Override Back Button // allow only if user is logged in
    override fun onBackPressed() {
        if(isLoggedIn) {
            super.onBackPressed()
        }
    }

    public fun setLoggedIn(logged: Boolean) {
        isLoggedIn = logged
    }

}