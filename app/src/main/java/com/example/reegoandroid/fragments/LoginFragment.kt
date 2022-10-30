package com.example.reegoandroid.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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

        return v
    }

    override fun onStart() {
        super.onStart()

        txtLoginTitulo.text = loginViewModel.tituloLogin


        btnLogin.setOnClickListener{

            loginViewModel.login()
            txtLoginTitulo.text = loginViewModel.tituloLogin
            println(usernameInput) // como envio esto al viewmodel?
            println(passwordInput)
        }

        btnHelp.setOnClickListener{
            val navigateLoginToHelp = LoginFragmentDirections.actionLoginFragmentToHelpFragment()

            v.findNavController().navigate(navigateLoginToHelp)
        }


    }


}