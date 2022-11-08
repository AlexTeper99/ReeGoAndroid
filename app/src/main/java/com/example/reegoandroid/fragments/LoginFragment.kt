package com.example.reegoandroid.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.reegoandroid.R
import com.example.reegoandroid.viewmodels.LoginViewModel
import com.example.reegoandroid.viewmodels.node.LoginData
import com.example.reegoandroid.viewmodels.node.NodeApi
import com.example.reegoandroid.viewmodels.node.NodeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    //declaro vista
    lateinit var v: View

    private var resultSaveSP : Boolean = false
    private lateinit var txtLoginTitulo : TextView
    private lateinit var btnLogin: Button
    private lateinit var btnHelp: Button
    private lateinit var usernameInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var btnAdmin: Button
    private val nodeRepository = NodeRepository(NodeApi.instance!!)
    companion object {
        fun newInstance() = LoginFragment()
    }

   private val loginViewModel : LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_login, container, false)

        //findViewByid y codigo
        txtLoginTitulo = v.findViewById(R.id.txtLoginTitle)
        btnLogin = v.findViewById(R.id.btnLogin)
        btnHelp = v.findViewById(R.id.btnHelp)
        usernameInput = v.findViewById(R.id.usernameInput)
        passwordInput = v.findViewById(R.id.passwordInput)
        btnAdmin = v.findViewById(R.id.adminBtn)

        return v
    }

    override fun onStart() {
        super.onStart()

        txtLoginTitulo.text = loginViewModel.tituloLogin


        btnLogin.setOnClickListener{


          //  val loginInfo = loginViewModel.login(usernameInput.text.toString(), passwordInput.text.toString())
            txtLoginTitulo.text = loginViewModel.tituloLogin

            val scope = CoroutineScope(Dispatchers.Main)
            var loginInfo: LoginData
            scope.launch {

                val result = nodeRepository.loginUser(usernameInput.text.toString(),  passwordInput.text.toString())

                result.onSuccess {
                    loginInfo = it
                    println("Login infoo corrutine")
                    println("---------------------------")
                    Log.d("idUser", loginInfo.idUser.toString())
                    Log.d("idPlot", loginInfo.idPlot.toString())
                    Log.d("city", loginInfo.city)
                    Log.d("isAdmin", loginInfo.isAdmin.toString())

                    resultSaveSP  = saveSharedPreferences(loginInfo.idUser.toString(), loginInfo.idPlot.toString(),  loginInfo.city,  loginInfo.isAdmin.toString() )


                    if(resultSaveSP){
                     v.findNavController().navigate(LoginFragmentDirections.actionLoginFragment3ToMainActivity())
                    }else{
                        println("No podes navegar burro")
                    }


                }.onFailure {
                    println("Error en a llamada al api -login User")
                    println("Error: ${it.message}")
                }
            }









        }



        btnHelp.setOnClickListener{
            val navigateLoginToHelp = LoginFragmentDirections.actionLoginFragmentToHelpFragment()

            v.findNavController().navigate(navigateLoginToHelp)
        }

        // Admin Screen / Should call after login with admin user
        btnAdmin.setOnClickListener{
            val navigateLoginToAdmin = LoginFragmentDirections.actionLoginFragmentToUserListFragment2()
            v.findNavController().navigate(navigateLoginToAdmin)
        }


    }

    private fun saveSharedPreferences(
        idUser: String,
        idPlot: String,
        city: String,
        isAdmin: String
    ): Boolean {
        val sharedPref : SharedPreferences = requireContext().getSharedPreferences("Credenciales", Context.MODE_PRIVATE)

        val editor = sharedPref.edit()

        editor.putString("UserId", idUser)
        editor.putString("UserPlot", idPlot)
        editor.putString("IsAdmin",isAdmin)
        editor.putString("City",city)
        editor.apply()

        var userId = sharedPref.getString("UserId", "FALLA SP")!!
        var plotId = sharedPref.getString("UserPlot", "FALLA SP")!!
        var city = sharedPref.getString("City", "FALLA SP")!!
        var isAdmin = sharedPref.getString("IsAdmin", "FALLA SP")!!
        println("shared preferences guardadas")
        println(userId)
        println(plotId)
        println(city)
        println(isAdmin)

        if(userId != "FALLA SP" && userId != "0"){
            println("LOGIN CORRECTO")
            return true;
        }else{
            println("LOGIN INCORRECTO - INGRESA LOS DATOS BIEN")
            return false
        }
    }


}