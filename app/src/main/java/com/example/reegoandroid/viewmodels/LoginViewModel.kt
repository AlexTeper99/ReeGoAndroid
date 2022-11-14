package com.example.reegoandroid.viewmodels

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import com.example.reegoandroid.fragments.LoginFragment
import com.example.reegoandroid.viewmodels.node.LoginData
import com.example.reegoandroid.viewmodels.node.NodeApi
import com.example.reegoandroid.viewmodels.node.NodeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel (private val nodeRepository: NodeRepository = NodeRepository(NodeApi.instance!!)): ViewModel() {
    var tituloLogin = "INICIAR SESION"



//    fun login(email: String, password: String): LoginData {
//        val scope = CoroutineScope(Dispatchers.Default)
//        var loginInfo : LoginData = LoginData(-1,-1,"-1", false)
//        scope.launch {
//
//            val result = nodeRepository.loginUser(email, password)
//
//            result.onSuccess {
//                loginInfo = it
//                println("Login infoo in view model")
//                println("---------------------------")
//                Log.d("idUser", loginInfo.idUser.toString())
//                Log.d("idPlot", loginInfo.idPlot.toString())
//                Log.d("city", loginInfo.city)
//                Log.d("isAdmin", loginInfo.isAdmin.toString())
//            }.onFailure {
//                println("Error en a llamada al api -login User")
//                println("Error: ${it.message}")
//            }
//        }
//
//        return loginInfo
//
//
//
//
//
//    }



}