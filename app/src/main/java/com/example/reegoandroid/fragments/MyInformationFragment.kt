package com.example.reegoandroid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.reegoandroid.R
import com.example.reegoandroid.viewmodels.MyInformationViewModel

class MyInformationFragment : Fragment() {
    lateinit var v: View
    private lateinit var txtTitle : TextView

    private lateinit var atmCondition : TextView
    private lateinit var humidityView : TextView
    private lateinit var locationView : TextView
    private lateinit var rainingView : TextView
    private lateinit var rainDescView : TextView
    private lateinit var temperatureView : TextView
    private lateinit var timeView : TextView

    private val myInformationViewModel : MyInformationViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_my_information, container, false)

        txtTitle = v.findViewById(R.id.txtMyInformationTitle)
        
        atmCondition     = v.findViewById(R.id.atmCondition)
        humidityView     = v.findViewById(R.id.humidity)
        locationView     = v.findViewById(R.id.location)
        rainDescView     = v.findViewById(R.id.rainDesc)
        rainingView      = v.findViewById(R.id.itsRaining)
        temperatureView  = v.findViewById(R.id.temperature)
        timeView         = v.findViewById(R.id.timeTextView)

        txtTitle.text = "Informacion Climatica"
        myInformationViewModel.getClimateData()

        var atmCondition = Observer<String> { atmospheric_condition ->
            atmCondition.text = "Condiciones Atmosfericas: $atmospheric_condition"
        }

        var humidity = Observer<String> { humidity ->
            humidityView.text = "Humedad: $humidity"
        }

        var location = Observer<String> { location ->
            locationView.text = "Ubicacion: $location"
        }

        var rainDesc = Observer<String> { raind_desc ->
            rainDescView.text = "Descripcion de la lluvia: $raind_desc"
        }

        var raining = Observer<String> { raining ->
            rainingView.text = "Esta lloviendo: $raining"
        }

        var time = Observer<String> { time ->
            timeView.text = "Horario medición: $time"
        }

        var temperature = Observer<String> { temperature ->
            temperatureView.text = "Temperatura: $temperature"
        }  


        myInformationViewModel.atmospheric_contition.observe(viewLifecycleOwner, atmCondition)
        myInformationViewModel.humidity.observe(viewLifecycleOwner, humidity)
        myInformationViewModel.location.observe(viewLifecycleOwner, location)
        myInformationViewModel.raining.observe(viewLifecycleOwner, raining)
        myInformationViewModel.rainDesc.observe(viewLifecycleOwner, rainDesc)
        myInformationViewModel.time.observe(viewLifecycleOwner, time)
        myInformationViewModel.temperature.observe(viewLifecycleOwner, temperature)

        
        return v
    }

    override fun onStart() {
        super.onStart()

    }

}