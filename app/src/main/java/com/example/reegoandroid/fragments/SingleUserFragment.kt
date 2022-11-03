package com.example.reegoandroid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.reegoandroid.R
import com.example.reegoandroid.viewmodels.SingleUserViewModel

class SingleUserFragment : Fragment() {
    lateinit var v: View
    private lateinit var txtTitle : TextView

    private val singleUserViewModel : SingleUserViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_single_user, container, false)

        txtTitle = v.findViewById(R.id.txtSingleUserTitle)
        return v
    }

    override fun onStart() {
        super.onStart()

        val myText = SingleUserFragmentArgs.fromBundle(requireArguments()).userId

        txtTitle.text = "Configurar Usuario " + myText

    }

}