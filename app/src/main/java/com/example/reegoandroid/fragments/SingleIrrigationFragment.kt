package com.example.reegoandroid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reegoandroid.R
import com.example.reegoandroid.adapters.NoteAdapter
import com.example.reegoandroid.viewmodels.SingleIrrigationViewModel

class SingleIrrigationFragment : Fragment() {
    lateinit var v: View
    private lateinit var txtTitle : TextView
    private lateinit var txtIrrigationId : TextView
    private lateinit var txtIrrigationIdValue : TextView
    private lateinit var txtIrrigationDate: TextView
    private lateinit var txtIrrigationDateValue: TextView
    private lateinit var txtIrrigationWater: TextView
    private lateinit var txtIrrigationWaterValue: TextView
    lateinit var btnAddComment: Button


    lateinit var notesRecyclerView: RecyclerView

    private val singleIrrigationViewModel : SingleIrrigationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        v =  inflater.inflate(R.layout.fragment_single_irrigation, container, false)

        notesRecyclerView = v.findViewById(R.id.recViewNotes)

        txtTitle = v.findViewById(R.id.txtSingleIrrigationTitle)
        txtIrrigationId = v.findViewById(R.id.txtIrrigationId)
        txtIrrigationIdValue = v.findViewById(R.id.txtIrrigationIdValue)

        txtIrrigationDate = v.findViewById(R.id.txtIrrigationDate)
        txtIrrigationDateValue = v.findViewById(R.id.txtIrrigationDateValue)
        txtIrrigationWater = v.findViewById(R.id.txtIrrigationWater)
        txtIrrigationWaterValue = v.findViewById(R.id.txtIrrigationWaterValue)

        btnAddComment = v.findViewById(R.id.btnNewComment)

        return v
    }

    override fun onStart() {
        super.onStart()

        txtTitle.text = "Comentar sobre este riego"
        txtIrrigationIdValue.text = SingleIrrigationFragmentArgs.fromBundle(requireArguments()).irrigation

        val irrigationId: Int = txtIrrigationIdValue.text.toString().toInt()

        // Call api method and get comments
        singleIrrigationViewModel.getComments(irrigationId)

        // For each comment, click on a single comment / edit a comment -> redirect to edit
        singleIrrigationViewModel.commentListLive.observe(viewLifecycleOwner) { commentList ->

            val noteAdapter = NoteAdapter(commentList.toMutableList()) { pos: Int ->
                val action =
                    SingleIrrigationFragmentDirections.actionSingleIrrigationFragmentToSingleNoteFragment(
                        commentList[pos].text,
                        commentList[pos].id,
                        irrigationId,
                        isEdit = false
                    )
                v.findNavController().navigate(action)
            }

            noteAdapter.notifyDataSetChanged()

            notesRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = noteAdapter
            }
        }

        // Create a comment / redirect to create comment
        btnAddComment.setOnClickListener {
            println("add a comment pressed")

            val action =
                SingleIrrigationFragmentDirections.actionSingleIrrigationFragmentToSingleNoteFragment(
                    "",
                    0,
                    irrigationId,
                    isEdit = false,
                    )
            v.findNavController().navigate(action)
        }

    }

}