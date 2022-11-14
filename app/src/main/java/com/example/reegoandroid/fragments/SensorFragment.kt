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

    private lateinit var cropType : TextView
    private lateinit var minTemp : TextView
    private lateinit var minPh : TextView
    private lateinit var maxHum : TextView
    // private lateinit var idPlot : TextView
    // private lateinit var idCrop : TextView
    private lateinit var plotDesc : TextView


    private val sensorViewModel : SensorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_sensor, container, false)

        // Sensor Info
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

        /// Plot Info

        sensorViewModel.getSetPlotData()

        cropType = v.findViewById(R.id.cropType)
        minTemp = v.findViewById(R.id.minTemp)
        minPh = v.findViewById(R.id.minPh)
        maxHum = v.findViewById(R.id.maxHum)
        plotDesc = v.findViewById(R.id.plotDesc)

        val cropTypeObserver = Observer<String> { cropType ->
            this.cropType.text = "Tipo de Cultivo: $cropType"
        }
        val minTempObserver = Observer<String> { minTemp ->
            this.minTemp.text = "Temperatura Mínima: $minTemp° C"
        }
        val minPhObserver = Observer<String> { minPh ->
            this.minPh.text = "PH Mínimo: $minPh"
        }
        val maxHumObserver = Observer<String> { maxHum ->
            this.maxHum.text = "Humedad Máxima: $maxHum %"
        }
        val descObserver = Observer<String> { plotDesc ->
            this.plotDesc.text = "Descripción: $plotDesc"
        }

        sensorViewModel.cropType.observe(viewLifecycleOwner, cropTypeObserver)
        sensorViewModel.minTemp.observe(viewLifecycleOwner, minTempObserver)
        sensorViewModel.minPh.observe(viewLifecycleOwner, minPhObserver)
        sensorViewModel.maxHum.observe(viewLifecycleOwner, maxHumObserver)
        sensorViewModel.desc.observe(viewLifecycleOwner, descObserver)


        return v

    }

    override fun onStart() {
        super.onStart()

    }

}