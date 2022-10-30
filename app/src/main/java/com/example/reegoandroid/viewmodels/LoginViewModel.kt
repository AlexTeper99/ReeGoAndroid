package com.example.reegoandroid.viewmodels

import androidx.lifecycle.ViewModel
import com.example.reegoandroid.fragments.LoginFragment

class LoginViewModel : ViewModel() {
    var tituloLogin = "INICIAR SESION"

    fun login() {
        tituloLogin = "SESION INICIADA"

    }
}