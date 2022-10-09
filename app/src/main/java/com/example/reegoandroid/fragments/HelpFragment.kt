package com.example.reegoandroid.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.reegoandroid.R
import com.example.reegoandroid.viewmodels.HelpViewModel

class HelpFragment : Fragment() {
    lateinit var v: View
    private lateinit var txtHelp : TextView

    private val helpViewModel : HelpViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_help, container, false)

        //findViewById & codigo
        txtHelp = v.findViewById(R.id.txtLabelHelp)
        return v
    }

    override fun onStart() {
        super.onStart()

       txtHelp.text = helpViewModel.textHelp


    }



}

