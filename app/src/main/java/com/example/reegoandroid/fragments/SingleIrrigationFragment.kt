package com.example.reegoandroid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        return v
    }

    override fun onStart() {
        super.onStart()

        txtTitle.text = "Comentar sobre este riego"
        txtIrrigationIdValue.text = SingleIrrigationFragmentArgs.fromBundle(requireArguments()).irrigation

        // TODO: GET THIS BY LOCAL STORAGE // get comments of a given irrigation id
        singleIrrigationViewModel.getComments(
            // Integer.parseInt(txtIrrigationId.text.toString())
            1
        )

        singleIrrigationViewModel.commentListLive.observe(viewLifecycleOwner) { commentList ->

            var noteAdapter = NoteAdapter(commentList.toMutableList()) { pos: Int ->
                val action =
                    SingleIrrigationFragmentDirections.actionSingleIrrigationFragmentToSingleNoteFragment(
                        commentList[pos].text, commentList[pos].id
                    )
                v.findNavController().navigate(action)
            }

            noteAdapter.notifyDataSetChanged()

            notesRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = noteAdapter
            }

        }


    }

}