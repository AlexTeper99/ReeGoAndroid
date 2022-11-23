package com.example.reegoandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    //setup activity principal (no toca ninguna pantalla).

    private lateinit var bottomNavView : BottomNavigationView //declaro bottom bar
    private lateinit var navHostFragment : NavHostFragment //declaro la navigation
    //vistas pantallas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        bottomNavView = findViewById(R.id.bottomNav) //busco bottonNav
        NavigationUI.setupWithNavController(bottomNavView, navHostFragment.navController) //declarotodo en la pantalla
    }
}