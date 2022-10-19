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

        titleText.text = "Datos del Sensor"
        sensorViewModel.getSetApiData()

        val teObserver = Observer<String> { temperature ->
            teIndicator.text = "Temperatura Ambiente: $temperature° C"
        }
        val huObserver = Observer<String> { humidity ->
            huIndicator.text = "Humedad del Suelo: $humidity %"
        }
        val acObserver = Observer<String> { acidity ->
            acIndicator.text = "PH del Suelo: $acidity"
        }

        // Observe model data
        sensorViewModel.temperature.observe(viewLifecycleOwner, teObserver)
        sensorViewModel.humidity.observe(viewLifecycleOwner, huObserver)
        sensorViewModel.acidity.observe(viewLifecycleOwner, acObserver)

        return v

    }

    override fun onStart() {
        super.onStart()

    }

}