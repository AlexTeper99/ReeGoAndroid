package com.example.reegoandroid.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reegoandroid.viewmodels.node.NodeApi
import com.example.reegoandroid.viewmodels.node.NodeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MyInformationViewModel(
    private val nodeRepository: NodeRepository = NodeRepository(NodeApi.instance!!)
) : ViewModel() {
//        init {
//             this@MyInformationViewModel.getClimateData("")
//        }

    val atmospheric_contition: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val humidity: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val location: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val rainDesc: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val raining: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val temperature: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val time: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }


    internal fun setClimateData(atmospheric_contition: String, humidity: String, location: String, rainDesc: String, raining: Boolean, temperature: String, time: String) {
        this.atmospheric_contition.postValue(atmospheric_contition)
        this.humidity.postValue(humidity)
        this.location.postValue(location)
        this.rainDesc.postValue(rainDesc)

        if(raining) {
            this.raining.postValue("Si")
        } else {
            this.raining.postValue("No")
        }

        this.temperature.postValue(temperature)
        this.time.postValue(time)
    }

    internal fun getClimateData(city: String) {

        val scope = CoroutineScope(Dispatchers.Default)

        //
        scope.launch(Dispatchers.Default) {


            val result = nodeRepository.getClimateData(city)

            result.onSuccess {
                val climateInfo = it
                println("Climate Info - From Climate Fake Api")
                println("---------------------------")
                println(climateInfo.atmospheric_contition)
                println(climateInfo.humidity)
                println(climateInfo.location)
                println(climateInfo.rain_desc)
                println(climateInfo.raining)
                println(climateInfo.temperature)
                println(climateInfo.time)
                println("---------------------------")

                // All have to be Strings in the mutable lists

                var ac = climateInfo.atmospheric_contition
                var hu = climateInfo.humidity.toString()
                var lo = climateInfo.location
                var rd = climateInfo.rain_desc
                var ra = climateInfo.raining
                var te = climateInfo.temperature.toString()
                var ti = climateInfo.time

                this@MyInformationViewModel.setClimateData(ac,hu,lo,rd,ra,te,ti)

            }.onFailure {
                println("ERROR En la llamada al api Climate Info")
                println("Error: ${it.message}")
            }
            
        }
    }
}

    

