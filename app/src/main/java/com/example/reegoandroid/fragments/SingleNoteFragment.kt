package com.example.reegoandroid.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.reegoandroid.R
import com.example.reegoandroid.viewmodels.SingleNoteViewModel
import com.google.android.material.snackbar.Snackbar

class SingleNoteFragment : Fragment() {
    lateinit var v: View
    // private lateinit var txtTitle : TextView

    private lateinit var txtNoteEditable : TextView
    private lateinit var btnUpdateComment: Button
    private lateinit var btnDeleteComment: Button
    private lateinit var btnCreateComment: Button


    private val singleNoteViewModel : SingleNoteViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_single_note, container, false)

        txtNoteEditable = v.findViewById(R.id.singleCommentEditText)

        btnCreateComment = v.findViewById(R.id.createNoteBtn)
        btnUpdateComment = v.findViewById(R.id.updateNoteTextBtn)
        btnDeleteComment = v.findViewById(R.id.deleteNoteBtn)

        return v

    }

    override fun onStart() {
        super.onStart()

        val noteId = SingleNoteFragmentArgs.fromBundle(requireArguments()).noteId
        val irrigationId = SingleNoteFragmentArgs.fromBundle(requireArguments()).irrigationId
        val noteText = SingleNoteFragmentArgs.fromBundle(requireArguments()).noteText

        txtNoteEditable.text = noteText


        val isEdit = SingleNoteFragmentArgs.fromBundle(requireArguments()).isEdit

        if (isEdit) {
            btnCreateComment.isVisible = false
            btnUpdateComment.isVisible = true
            btnDeleteComment.isVisible = true
        } else {
            btnCreateComment.isVisible = true
            btnUpdateComment.isVisible = false
            btnDeleteComment.isVisible = false
        }


        // Create a comment
        btnCreateComment.setOnClickListener {

            val newText = txtNoteEditable.text.toString()

            var validInput = singleNoteViewModel.validateInputs(newText)

            if(validInput) {
                singleNoteViewModel.createComment(newText,irrigationId)
                v.findNavController().navigate(SingleNoteFragmentDirections.actionSingleNoteFragmentToIrrigationListFragment())
            } else {
                // invalid inputs
                var snackbar =  Snackbar.make(v, "Ingreso de texto no valido",
                    Snackbar.LENGTH_SHORT).setAction("Action", null)
                var sbView = snackbar.view
                sbView.setBackgroundColor(Color.RED)
                snackbar.show()
            }

        }

        // Update a comment
        btnUpdateComment.setOnClickListener {

            val newText = txtNoteEditable.text.toString()
            var validInput = singleNoteViewModel.validateInputs(newText)

            if(validInput) {
                singleNoteViewModel.updateComment(noteId, newText)
                v.findNavController().navigate(SingleNoteFragmentDirections.actionSingleNoteFragmentToIrrigationListFragment())
            } else {
                // invalid inputs
                var snackbar =  Snackbar.make(v, "Ingreso de texto no valido",
                    Snackbar.LENGTH_SHORT).setAction("Action", null)
                var sbView = snackbar.view
                sbView.setBackgroundColor(Color.RED)
                snackbar.show()
            }

        }

        // Delete a comment by id
        btnDeleteComment.setOnClickListener {
            singleNoteViewModel.deleteComment(noteId)
            v.findNavController().navigate(SingleNoteFragmentDirections.actionSingleNoteFragmentToIrrigationListFragment())
        }


    }



}