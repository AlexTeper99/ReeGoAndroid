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
import com.example.reegoandroid.viewmodels.HelpViewModel

class BackofficeFragment : Fragment() {
    lateinit var v: View
    private lateinit var txtTitle : TextView

    private val backofficeViewModel : BackofficeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_backoffice, container, false)

        txtTitle = v.findViewById(R.id.txtBackofficeTitle)
        return v
    }

    override fun onStart() {
        super.onStart()

       txtTitle.text = "ReeGo"
    }



}