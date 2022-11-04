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

    suspend fun getIrrigationComments(irrId: Int): Result<List<NoteData>>
    {
        return try {

            val response = api.getIrrigationComments(irrId)
            Result.success(response)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateComment(commentId: Int, commentText : String): Result<String> {
        return try {
            val response = api.updateComment(commentId,commentText)
            Result.success(response)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }



}