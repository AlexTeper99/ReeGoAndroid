package com.example.reegoandroid.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.reegoandroid.R
import com.example.reegoandroid.viewmodels.node.IrrigationData

class IrrigationAdapter(var irrigationList: MutableList<IrrigationData>) : RecyclerView.Adapter<IrrigationAdapter.IrrigationHolder>() {
    class IrrigationHolder (v: View) : RecyclerView.ViewHolder(v) {
        //creo una vista
        private var view: View
        init {
            this.view = v
        }

        fun setWater(liters : String){
            var txtWater : TextView = view.findViewById(R.id.txtWater)
            txtWater.text = liters
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IrrigationHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.item_irrigation,parent,false)
        return (IrrigationHolder(view))
    }

    override fun onBindViewHolder(holder: IrrigationHolder, position: Int) {

        holder.setWater(irrigationList[position].waterUsed.toString())
    }

    override fun getItemCount(): Int {
        return irrigationList.size
    }
}