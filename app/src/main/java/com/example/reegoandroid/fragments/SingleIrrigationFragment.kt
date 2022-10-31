package com.example.reegoandroid.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.example.reegoandroid.R
import com.example.reegoandroid.viewmodels.BackofficeViewModel
import com.example.reegoandroid.viewmodels.SingleIrrigationViewModel

class SingleIrrigationFragment : Fragment() {
    lateinit var v: View
    private lateinit var txtTitle : TextView
    private lateinit var txtIrrigationId : TextView
    private lateinit var txtIrrigationIdValue : TextView
    private val singleIrrigationViewModel : SingleIrrigationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v =  inflater.inflate(R.layout.fragment_single_irrigation, container, false)

        txtTitle = v.findViewById(R.id.txtSingleIrrigationTitle)
        txtIrrigationId = v.findViewById(R.id.txtIrrigationId)
        txtIrrigationIdValue = v.findViewById(R.id.txtIrrigationIdValue)
        return v
    }

    override fun onStart() {
        super.onStart()

        txtTitle.text = "Info del riego"
        txtIrrigationIdValue.text = SingleIrrigationFragmentArgs.fromBundle(requireArguments()).irrigation
    }

}