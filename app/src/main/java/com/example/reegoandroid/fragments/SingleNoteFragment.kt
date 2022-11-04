package com.example.reegoandroid.fragments

import android.os.Bundle
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


    private val singleNoteViewModel : SingleNoteViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_single_note, container, false)


        // txtTitle = v.findViewById(R.id.txtSingleNoteTitle)
        txtNoteEditable = v.findViewById(R.id.singleCommentEditText)

        btnUpdateComment = v.findViewById(R.id.updateNoteTextBtn)
        btnDeleteComment = v.findViewById(R.id.deleteNoteBtn)
        return v
    }

    override fun onStart() {
        super.onStart()

        val riegoId = SingleNoteFragmentArgs.fromBundle(requireArguments()).noteId
        val noteText = SingleNoteFragmentArgs.fromBundle(requireArguments()).noteText

        txtNoteEditable.text = noteText


        // Update a comment
        btnUpdateComment.setOnClickListener {

            val newText = txtNoteEditable.text.toString()

            singleNoteViewModel.updateComment(riegoId, newText)

        }

        // Delete a comment by id

        btnDeleteComment.setOnClickListener {
            println("Delete comment $riegoId")
            // singleNoteViewModel.deleteComment(riegoId)
        }

    }



}