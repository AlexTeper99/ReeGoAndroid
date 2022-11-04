package com.example.reegoandroid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reegoandroid.R
import com.example.reegoandroid.adapters.IrrigationAdapter
import com.example.reegoandroid.viewmodels.IrrigationListViewModel

class IrrigationListFragment : Fragment() {
    lateinit var v: View

    private val irrigationListViewModel : IrrigationListViewModel by viewModels()

    // 4. inicializar el adapter
    lateinit var irrigationAdapter: IrrigationAdapter
    lateinit var irrigationRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v =  inflater.inflate(R.layout.fragment_irrigation_list, container, false)

        irrigationRecyclerView = v.findViewById(R.id.recIrrigation)


        return v
    }

    override fun onStart() {
        super.onStart()

        irrigationListViewModel.getIrrigationList();

        irrigationListViewModel.irrigationListLive.observe(viewLifecycleOwner) { irrigationList ->

            irrigationAdapter = IrrigationAdapter(irrigationList.toMutableList()) { pos ->
                val action = IrrigationListFragmentDirections.actionIrrigationListFragmentToSingleIrrigationFragment2(irrigationList[pos].id.toString())
                v.findNavController().navigate(action)
            }
            irrigationAdapter.notifyDataSetChanged()

            irrigationRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = irrigationAdapter
            }

        }

        // TODO + Comentario onClick() redirect to
        // new comentario
        // single note fragment

    }

}