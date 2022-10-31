package com.example.reegoandroid.viewmodels.node

class NodeRepository(private val api: NodeApi) {
    // returns climate data
    suspend fun getClimateData(): Result<ClimateData>
    {
        return try {


            val response = api.getClimateData()
            Result.success(response)
        
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getIrrigationList(): Result<List<IrrigationData>>
    {
        return try {

            val response = api.getIrrigationList()
            Result.success(response) 
            
            } catch (e: Exception) {
                Result.failure(e)
        }
    } 

    
    suspend fun getAllusers(): Result<MutableList<UserData>>
    {
        return try {

            val response = api.getUserList()
            Result.success(response)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}