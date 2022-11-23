package com.example.reegoandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment

private lateinit var navHostFragment2 : NavHostFragment //declaro la navigation

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        navHostFragment2 = supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment

    }
}