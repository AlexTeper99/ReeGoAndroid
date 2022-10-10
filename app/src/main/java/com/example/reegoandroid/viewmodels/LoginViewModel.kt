package com.example.reegoandroid.viewmodels

import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    var tituloLogin = "INICIAR SESION"

    fun iniciarSesion() {
        tituloLogin = "INICIO SESION metodo"
    }
}