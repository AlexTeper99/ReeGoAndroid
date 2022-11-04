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
    init {
        this@IrrigationListViewModel.getIrrigationList()
    }

    //Declaro las propiedades
    var irrigationList: MutableList<IrrigationData> = mutableListOf()

    val irrigationListLive: MutableLiveData<List<IrrigationData>> by lazy {
        MutableLiveData<List<IrrigationData>>()
    }


    internal fun getIrrigationList() {


        val scope = CoroutineScope(Dispatchers.Default)

        scope.launch(Dispatchers.Default) {
            val result = nodeRepository.getIrrigationList()

            result.onSuccess {
                val irrigationList = it
                irrigationListLive.postValue(it)
                println("Irrigation List- From Climate Fake Api")
                println("---------------------------")
                println(irrigationList[0].idPlot.toString())

                println("---------------------------")
                println(irrigationList[1].idPlot.toString())
                // All have to be Strings in the mutable lists


            }.onFailure {
                println("ERROR En la llamada al api")
                println("Error: ${it.message}")
            }
        }
    }

//    internal fun createComment(irrigationId: Int, commentText: String) {
//        val scope = CoroutineScope(Dispatchers.Default)
//        scope.launch {
//
//            val result = nodeRepository.createComment(commentText, irrigationId);
//
//            result.onSuccess {
//                println("Comentario Creado" )
//            }.onFailure {
//                println("Error en a llamada al api - Create Comment")
//            }
//
//        }
//    }
}