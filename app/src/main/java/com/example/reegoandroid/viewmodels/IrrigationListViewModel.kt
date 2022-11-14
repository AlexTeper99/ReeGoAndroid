package com.example.reegoandroid.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reegoandroid.viewmodels.node.IrrigationData
import com.example.reegoandroid.viewmodels.node.NodeApi
import com.example.reegoandroid.viewmodels.node.NodeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IrrigationListViewModel( private val nodeRepository: NodeRepository = NodeRepository(NodeApi.instance!!))
    : ViewModel() {
//    init {
//        this@IrrigationListViewModel.getIrrigationList(idPlot : Int)
//    }

    //Declaro las propiedades
    var irrigationList: MutableList<IrrigationData> = mutableListOf()

    val irrigationListLive: MutableLiveData<List<IrrigationData>> by lazy {
        MutableLiveData<List<IrrigationData>>()
    }

    fun clearData() {
        irrigationListLive.value = null
    }

    internal fun getIrrigationList(idPlot : Int) {


        val scope = CoroutineScope(Dispatchers.Default)

        scope.launch(Dispatchers.Default) {
            val result = nodeRepository.getIrrigationList(idPlot)

            result.onSuccess {
                val irrigationList = it
                irrigationListLive.postValue(it)
                // All have to be Strings in the mutable lists


            }.onFailure {
                println("ERROR En la llamada al api")
                println("Error: ${it.message}")
            }
        }
    }







}