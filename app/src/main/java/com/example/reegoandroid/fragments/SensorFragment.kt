package com.example.reegoandroid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.reegoandroid.R
import com.example.reegoandroid.viewmodels.SensorViewModel


class SensorFragment : Fragment() {
    lateinit var v: View
    private lateinit var titleText : TextView

    private lateinit var teIndicator : TextView
    private lateinit var acIndicator : TextView
    private lateinit var huIndicator : TextView


    private val sensorViewModel : SensorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_sensor, container, false)

        titleText = v.findViewById(R.id.txtSensorTitle)

        teIndicator = v.findViewById(R.id.tempIndicator)
        acIndicator = v.findViewById(R.id.acidIndicator)
        huIndicator = v.findViewById(R.id.humiIndicator)

        sensorViewModel.getSetApiData()

        titleText.text   = sensorViewModel.dataString
        teIndicator.text = "Temperatura Ambiente: " + sensorViewModel.getTemp() + "° C"
        huIndicator.text = "Humedad del Suelo: " + sensorViewModel.getHum() + " %"
        acIndicator.text = "PH del Suelo: " + sensorViewModel.getAcid()

        return v

    }

    override fun onStart() {
        super.onStart()

    }

}