package com.example.reegoandroid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.reegoandroid.MainActivity2
import com.example.reegoandroid.R
import com.example.reegoandroid.viewmodels.HelpViewModel

class HelpFragment : Fragment() {
    lateinit var v: View

    private lateinit var txtHelpTitle : TextView
    private lateinit var txtHelp : TextView


    private val helpViewModel : HelpViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_help, container, false)

        //findViewById & codigo
        txtHelpTitle = v.findViewById(R.id.txtTitleHelp)
        txtHelp = v.findViewById(R.id.txtLabelHelp)
        return v
    }

    override fun onStart() {
        super.onStart()
        txtHelpTitle.text = helpViewModel.titleText
        txtHelp.text = helpViewModel.textHelp
        (activity as MainActivity2?)?.setLoggedIn(true)
    }



}

