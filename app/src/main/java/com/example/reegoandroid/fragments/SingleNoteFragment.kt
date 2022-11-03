package com.example.reegoandroid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.reegoandroid.R
import com.example.reegoandroid.viewmodels.SingleNoteViewModel

class SingleNoteFragment : Fragment() {
    lateinit var v: View
    // private lateinit var txtTitle : TextView
    private lateinit var txtNoteEditable : TextView


    private val singleNoteViewModel : SingleNoteViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_single_note, container, false)

        // txtTitle = v.findViewById(R.id.txtSingleNoteTitle)
        txtNoteEditable = v.findViewById(R.id.singleCommentEditText)

        return v
    }

    override fun onStart() {
        super.onStart()

        val riegoId = SingleNoteFragmentArgs.fromBundle(requireArguments()).noteId.toString()
        val noteText = SingleNoteFragmentArgs.fromBundle(requireArguments()).noteText

        txtNoteEditable.text = noteText

        // TODO

        // deleteButton on click delete(riegoId)

        // editButton on clicl update(riegoId, , txtNoteEditable.text)

    }



}