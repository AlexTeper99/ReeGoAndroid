package com.example.reegoandroid.viewmodels.fiware

class FiwareRepository(private val api: FiwareApi) {
// returns sensor data
    suspend fun getSensorData(): Result<SensorData>
    {
        return try {
        
            val response = api.getSensorData()
            Result.success(response)
        
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // returns plot data
    suspend fun getPlotData(plotId:String): Result<PlotData>
    {
        return try {

            val response = api.getPlotData(plotId)
            Result.success(response)
        
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}