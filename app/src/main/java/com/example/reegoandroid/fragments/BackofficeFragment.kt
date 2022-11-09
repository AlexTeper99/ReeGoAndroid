package com.example.reegoandroid.fragments

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.reegoandroid.R
import com.example.reegoandroid.viewmodels.BackofficeViewModel
import com.example.reegoandroid.viewmodels.HelpViewModel

class BackofficeFragment : Fragment() {
    lateinit var v: View
    private lateinit var txtTitle : TextView
    private lateinit var btnUserList : Button
    private lateinit var btnLogoutAdmin: Button

    private val backofficeViewModel : BackofficeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_backoffice, container, false)

        btnUserList = v.findViewById(R.id.btnUserList)
        txtTitle = v.findViewById(R.id.txtBackofficeTitle)
        btnLogoutAdmin = v.findViewById(R.id.btnLogoutAdmin)
        return v
    }

    override fun onStart() {
        super.onStart()

       txtTitle.text = "ReeGo"

        btnUserList.setOnClickListener{
            v.findNavController().navigate(BackofficeFragmentDirections.actionBackofficeFragmentToUserListFragment2())
        }

        btnLogoutAdmin.setOnClickListener{

            val sharedPref : SharedPreferences = requireContext().getSharedPreferences("Credenciales", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()

            editor.putString("UserId", "0")
            editor.putString("UserPlot", "0")
            editor.putBoolean("IsAdmin",false)
            editor.putString("City","")
            editor.apply()

            //TEST EN CONSOLA LAS SP
            var userId = sharedPref.getString("UserId", "FALLA SP LOGOUT")!!
            var plotId = sharedPref.getString("UserPlot", "FALLA SP LOGOUT")!!
            var city = sharedPref.getString("City", "FALLA SP LOGOUT")!!
            var isAdmin = sharedPref.getBoolean("IsAdmin", false)!!
            println("shared preferences reseteadas")
            println(userId)
            println(plotId)
            println(city)
            println(isAdmin)

            v.findNavController().navigate(BackofficeFragmentDirections.actionBackofficeFragmentToLoginFragment())


        }

    }



}