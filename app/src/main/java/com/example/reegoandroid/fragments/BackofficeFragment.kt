package com.example.reegoandroid.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.reegoandroid.R
import com.example.reegoandroid.viewmodels.BackofficeViewModel
import com.example.reegoandroid.viewmodels.HelpViewModel

class BackofficeFragment : Fragment() {
    lateinit var v: View
    private lateinit var txtTitle : TextView
    private lateinit var btnUserList : Button

    private val backofficeViewModel : BackofficeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_backoffice, container, false)

        btnUserList = v.findViewById(R.id.btnUserList)
        txtTitle = v.findViewById(R.id.txtBackofficeTitle)
        return v
    }

    override fun onStart() {
        super.onStart()

       txtTitle.text = "ReeGo"

        btnUserList.setOnClickListener{
            v.findNavController().navigate(BackofficeFragmentDirections.actionBackofficeFragmentToUserListFragment2())
        }

    }



}