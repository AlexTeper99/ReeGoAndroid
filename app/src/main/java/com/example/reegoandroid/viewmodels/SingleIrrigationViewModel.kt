package com.example.reegoandroid.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reegoandroid.viewmodels.node.NodeApi
import com.example.reegoandroid.viewmodels.node.NodeRepository
import com.example.reegoandroid.viewmodels.node.NoteData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SingleIrrigationViewModel(private val nodeRepository: NodeRepository = NodeRepository(NodeApi.instance!!)) : ViewModel() {
    init {
        // TODO get irrigation id

        this@SingleIrrigationViewModel.getComments(1)
    }

    var commentList: MutableList<NoteData> = mutableListOf()

    val commentListLive: MutableLiveData<List<NoteData>> by lazy {
        MutableLiveData<List<NoteData>>()
    }

    internal fun getComments(irrId:Int) {


        val scope = CoroutineScope(Dispatchers.Default)

        scope.launch(Dispatchers.Default) {
            val result = nodeRepository.getIrrigationComments(irrId)

            result.onSuccess {

                val commentList = it
                // observe in view
                commentListLive.postValue(it)

                println("Getting Comment list from Node Api")

                for (comment in commentList) {
                    println("comment " + comment.text)
                }


            }.onFailure {
                println("Error en la llamada Comments")
                println("Error: ${it.message}")
            }
        }
    }
}