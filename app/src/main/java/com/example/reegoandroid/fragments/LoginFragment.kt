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
import androidx.core.content.ContentProviderCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.reegoandroid.R
import com.example.reegoandroid.viewmodels.LoginViewModel
import org.w3c.dom.Text

class LoginFragment : Fragment() {

    //declaro vista
    lateinit var v: View

    private lateinit var txtLoginTitulo : TextView
    private lateinit var btnLogin: Button
    private lateinit var btnHelp: Button
    private lateinit var usernameInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var btnAdmin: Button

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

            val navigateToMainActivity = v.findNavController().navigate(LoginFragmentDirections.actionLoginFragment3ToMainActivity())
            //  v.findNavController().navigate(LoginFragmentDirections.actionLoginFragment3ToMainActivity())

            txtLoginTitulo.text = loginViewModel.tituloLogin
            println(usernameInput) // como envio esto al viewmodel?
            println(passwordInput)


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

    fun login(){
        //MANEJO SHARED PREFERENCES
        val sharedPref : SharedPreferences = requireContext().getSharedPreferences("Credenciales", Context.MODE_PRIVATE)

        val editor = sharedPref.edit()

        editor.putString("UserId", "1")
        editor.putString("UserPlot", "1")
        editor.putString("isAdmin", "1")
        editor.apply()

        var user = sharedPref.getString("UserId", "0")!!
        Log.d("-----UserId----------------", user)

        editor.putString("UserId", "0")
        editor.apply()
        user = sharedPref.getString("UserId", "0")!!
        Log.d("-----UserId ACTUALIZADO----------------", user)

        if(true){
            btnAdmin.visibility = View.INVISIBLE //
        }
    }


}