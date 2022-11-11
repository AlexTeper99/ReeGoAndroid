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
        this@SensorViewModel.getSetPlotData()
    }
    // Sensor data
    val temperature: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val humidity: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val acidity: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    
    // Plot data
    val desc: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val maxHum: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val idCrop: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val idPlot: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val minPh: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val minTemp: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val cropType: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }



    internal fun setSensorData(acidity: String, humidity: String, temperature: String) {
        this.acidity.postValue(acidity)
        this.humidity.postValue(humidity)
        this.temperature.postValue(temperature)
    }

    // set plot data
    internal fun setPlotData(desc: String, maxHum: String, idCrop: String, idPlot: String, minPh: String, minTemp: String, cropType: String) {
        this.desc.postValue(desc)
        this.maxHum.postValue(maxHum)
        this.idCrop.postValue(idCrop)
        this.idPlot.postValue(idPlot)
        this.minPh.postValue(minPh)
        this.minTemp.postValue(minTemp)
        this.cropType.postValue(cropType)
    }


    internal fun getSetApiData() {

        val scope = CoroutineScope(Dispatchers.Default)

        scope.launch(Dispatchers.Default) {
            val result = repository.getSensorData()

            result.onSuccess {
                val sensorInfo = it

                if (it != null) {
                    var ac = sensorInfo.acidity.value
                    var hu = sensorInfo.humidity.value
                    var te = sensorInfo.temperature.value

                    this@SensorViewModel.setSensorData(ac,hu,te)
                }

            }.onFailure {
                println("Error en Llamada al API Sensor")
                println("Error: ${it.message}")
            }
        }
    }

    internal fun getSetPlotData() {

        val scope = CoroutineScope(Dispatchers.Default)

        scope.launch(Dispatchers.Default) {

            // TODO !!!
            // GET PLOT ID FROM LOCAL STORAGE
            val plotId = "2"
            val result = repository.getPlotData(plotId)

            result.onSuccess {
                val plotInfo = it
                println("Plot Info - Node")
                println("---------------------------")
                
                println(plotInfo.idPlot)
                println(plotInfo.desc)
                
                println("----------------")

                var desc = plotInfo.desc
                var maxHum = plotInfo.humedadMax.toString()
                var idCrop = plotInfo.idCrop.toString()
                var idPlot = plotInfo.idPlot.toString()
                var minPh = plotInfo.phMinimo.toString()
                var minTemp = plotInfo.tempMinima.toString()
                var cropType = plotInfo.tipocultivo

                this@SensorViewModel.setPlotData(desc, maxHum, idCrop, idPlot, minPh, minTemp, cropType)

            }.onFailure {
                println("Error en Llamada al API Plot")
                println("Error: ${it.message}")
            }
        }
    }

    
}