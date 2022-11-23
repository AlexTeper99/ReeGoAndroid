package com.example.reegoandroid.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.reegoandroid.R
import com.example.reegoandroid.viewmodels.BackofficeViewModel

class BackofficeFragment : Fragment() {
    lateinit var v: View

    private lateinit var txtTitle   : TextView
    private lateinit var totalPlots : TextView
    private lateinit var totalTrigo : TextView
    private lateinit var totalSoja  : TextView
    private lateinit var totalMaiz  : TextView
    private lateinit var totalIrrigations : TextView

    private lateinit var btnUserList : Button
    private lateinit var btnLogoutAdmin: Button

    private val backofficeViewModel : BackofficeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_backoffice, container, false)

        btnUserList = v.findViewById(R.id.btnUserList)
        btnLogoutAdmin = v.findViewById(R.id.btnLogoutAdmin)

        txtTitle    = v.findViewById(R.id.txtBackofficeTitle)

        totalPlots = v.findViewById(R.id.txtTotalPlots)
        totalTrigo = v.findViewById(R.id.txtTotalTrigo)
        totalMaiz  = v.findViewById(R.id.txtTotalMaiz)
        totalSoja  = v.findViewById(R.id.txtTotalSoja)

        totalIrrigations = v.findViewById(R.id.txtTotalIrrigationsLog)


        // Call viewmodel and get params for each field
        backofficeViewModel.getAllInfo()

        var totalPlots = Observer<String> { plotCount ->
            totalPlots.text = "Cantidad de parcelas: $plotCount"
        }
        backofficeViewModel.plotCount.observe(viewLifecycleOwner, totalPlots)

        var totalSoja = Observer<String> { sojaCount ->
            totalSoja.text = "Cultivos de Soja: $sojaCount"
        }
        backofficeViewModel.sojaCount.observe(viewLifecycleOwner, totalSoja)

        var totalTrigo = Observer<String> { trigoCount ->
            totalTrigo.text = "Cultivos de Trigo: $trigoCount"
        }
        backofficeViewModel.trigoCount.observe(viewLifecycleOwner, totalTrigo)

        var totalMaiz = Observer<String> { maizCount ->
            totalMaiz.text = "Cultivos de Maiz: $maizCount"
        }
        backofficeViewModel.maizCount.observe(viewLifecycleOwner, totalMaiz)

        var irrigationsNumber = Observer<String> { irrigationsCount ->
            totalIrrigations.text = "Total de Riegos: $irrigationsCount"
        }
        backofficeViewModel.irrigationCount.observe(viewLifecycleOwner, irrigationsNumber)


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