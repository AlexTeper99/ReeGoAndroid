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
    private lateinit var txtTitle : TextView

    private val sensorViewModel : SensorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_sensor, container, false)

        txtTitle = v.findViewById(R.id.txtSensorTitle)
        sensorViewModel.getSetApiData()
        txtTitle.text = sensorViewModel.dataString

        return v

    }

    override fun onStart() {
        super.onStart()

    }

}