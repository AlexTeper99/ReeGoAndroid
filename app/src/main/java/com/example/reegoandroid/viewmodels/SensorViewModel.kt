package com.example.reegoandroid.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reegoandroid.viewmodels.fiware.FiwareApi
import com.example.reegoandroid.viewmodels.fiware.FiwareRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SensorViewModel(
    private val repository : FiwareRepository = FiwareRepository(FiwareApi.instance!!)
) : ViewModel() {
    init {
        this@SensorViewModel.getSetApiData()
    }

    val temperature: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val humidity: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val acidity: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    internal fun setSensorData(acidity: String, humidity: String, temperature: String) {
        this.acidity.postValue(acidity)

        this.humidity.postValue(humidity)

        this.temperature.postValue(temperature)

    }

    internal fun getSetApiData() {

        val scope = CoroutineScope(Dispatchers.Default)

        scope.launch(Dispatchers.Default) {
            val result = repository.getSensorData()

            result.onSuccess {
                val sensorInfo = it
                println("SENSOR INFO From Fiware Api")
                println("---------------------------")
                println(sensorInfo.acidity.value)
                println(sensorInfo.humidity.value)
                println(sensorInfo.temperature.value)
                println("---------------------------")

                var ac = sensorInfo.acidity.value
                var hu = sensorInfo.humidity.value
                var te = sensorInfo.temperature.value

                this@SensorViewModel.setSensorData(ac,hu,te)

            }.onFailure {
                println("Error en Llamada al API")
                println("Error: ${it.message}")
            }
        }
    }


    
}