package com.example.reegoandroid.viewmodels

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.ContentProviderCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import com.example.reegoandroid.fragments.LoginFragment

class LoginViewModel : ViewModel() {
    var tituloLogin = "INICIAR SESION"

    fun login() {
        tituloLogin = "SESION INICIADA"

    }

    fun testSharedPreferences(){


    }


}