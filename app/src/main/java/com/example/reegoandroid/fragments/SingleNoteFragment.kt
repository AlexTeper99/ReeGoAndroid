package com.example.reegoandroid.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.reegoandroid.R
import com.example.reegoandroid.viewmodels.SingleNoteViewModel

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

        val isEdit = SingleNoteFragmentArgs.fromBundle(requireArguments()).isEdit

        if (isEdit) {
            // TODO set visibility of edit true / create false
        } else {
            // TODO set visibility of create true / edit false
        }





        return v

    }

    override fun onStart() {
        super.onStart()

        val noteId = SingleNoteFragmentArgs.fromBundle(requireArguments()).noteId
        val irrigationId = SingleNoteFragmentArgs.fromBundle(requireArguments()).irrigationId
        val noteText = SingleNoteFragmentArgs.fromBundle(requireArguments()).noteText
        Log.d("NOTE ID", noteId.toString())


        txtNoteEditable.text = noteText


        // Create a comment
        btnCreateComment.setOnClickListener {

            val newText = txtNoteEditable.text.toString()
            singleNoteViewModel.createComment(newText,irrigationId)
            // Todo Redirect Back
        }

        // Update a comment
        btnUpdateComment.setOnClickListener {

            val newText = txtNoteEditable.text.toString()
            singleNoteViewModel.updateComment(noteId, newText)
            // Todo Redirect Back
        }

        // Delete a comment by id
        btnDeleteComment.setOnClickListener {
            singleNoteViewModel.deleteComment(noteId)
            // Todo Redirect Back
        }


    }



}