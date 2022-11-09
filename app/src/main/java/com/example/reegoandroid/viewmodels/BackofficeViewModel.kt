package com.example.reegoandroid.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reegoandroid.viewmodels.node.NodeApi
import com.example.reegoandroid.viewmodels.node.NodeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class BackofficeViewModel(
    private val nodeRepository: NodeRepository = NodeRepository(NodeApi.instance!!)
) : ViewModel() {
    init {
        this@BackofficeViewModel.getAllInfo()
    }

    val plotCount: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val trigoCount: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val sojaCount: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val maizCount: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val irrigationCount: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    internal fun setBackData(plotCount:String, trigoCount: String, sojaCount: String, maizCount: String, irrigationCount: String ) {
        this.plotCount.postValue(plotCount)
        this.trigoCount.postValue(trigoCount)
        this.sojaCount.postValue(sojaCount)
        this.maizCount.postValue(maizCount)
        this.irrigationCount.postValue(irrigationCount)
    }

    internal fun getAllInfo() {

        val scope = CoroutineScope(Dispatchers.Default)

        scope.launch(Dispatchers.Default) {
            val result = nodeRepository.getBackofficeInfo()

            result.onSuccess {
                val backInfo = it
                println("Getting backoffice stats data from API")

                var plotCount  = backInfo.cantplots
                var trigoCount = backInfo.cantTrigo
                var sojaCount  = backInfo.cantSoja
                var maizCount  = backInfo.cantMaiz
                var irrigationCount = backInfo.cantRiegosRealizados

                this@BackofficeViewModel.setBackData(
                    plotCount.toString(),
                    trigoCount.toString(),
                    sojaCount.toString(),
                    maizCount.toString(),
                    irrigationCount.toString()
                )

            }.onFailure {
                println("ERROR En la llamada al api /stats")
                println("Error: ${it.message}")
            }

        }
    }


}