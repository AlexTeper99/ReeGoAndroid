package com.example.reegoandroid.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reegoandroid.R
import com.example.reegoandroid.adapters.IrrigationAdapter
import com.example.reegoandroid.viewmodels.BackofficeViewModel
import com.example.reegoandroid.viewmodels.IrrigationListViewModel
import com.example.reegoandroid.viewmodels.node.IrrigationData

class IrrigationListFragment : Fragment() {
    lateinit var v: View

    //4. inicializar el adapter
    lateinit var adapter: IrrigationAdapter
    lateinit var recyclerView: RecyclerView

    //lateinit var irrigationList : MutableLiveData<MutableList<IrrigationData>>

    //var irrigationList: MutableLiveData<MutableList<IrrigationData>>

    private val irrigationListViewModel : IrrigationListViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v =  inflater.inflate(R.layout.fragment_irrigation_list, container, false)

        recyclerView = v.findViewById(R.id.recIrrigation)



        irrigationListViewModel.getIrrigationList()



        return v
    }

    override fun onStart() {
        super.onStart()

        irrigationListViewModel.getIrrigationList();

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        irrigationListViewModel.getIrrigationList()
        adapter = IrrigationAdapter(irrigationListViewModel.getVariableIrrigationList())
        recyclerView.adapter = adapter

    }

}