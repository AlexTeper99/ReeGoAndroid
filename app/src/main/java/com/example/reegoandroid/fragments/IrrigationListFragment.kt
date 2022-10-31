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
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reegoandroid.R
import com.example.reegoandroid.adapters.IrrigationAdapter
import com.example.reegoandroid.viewmodels.BackofficeViewModel
import com.example.reegoandroid.viewmodels.IrrigationListViewModel
import com.example.reegoandroid.viewmodels.node.IrrigationData
import com.google.android.material.snackbar.Snackbar

class IrrigationListFragment : Fragment() {
    lateinit var v: View

    private val irrigationListViewModel : IrrigationListViewModel by viewModels()

    //4. inicializar el adapter
    lateinit var irrigationAdapter: IrrigationAdapter
    lateinit var irrigationRecyclerView: RecyclerView

    //lateinit var irrigationList : MutableLiveData<MutableList<IrrigationData>>

    //var irrigationList: MutableLiveData<MutableList<IrrigationData>>




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

       // recyclerView.layoutManager = LinearLayoutManager(requireContext())
        // irrigationListViewModel.getIrrigationList()
    //    adapter = IrrigationAdapter(irrigationListViewModel.getVariableIrrigationList())
     //   recyclerView.adapter = adapter
        irrigationListViewModel.irrigationListLive.observe(viewLifecycleOwner) { irrigationList ->

           // userAdapter = UserAdapter(userList.toMutableList())
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
    }

}