package com.example.reegoandroid.viewmodels.node

class NodeRepository(private val api: NodeApi) {
    // returns climate data
    suspend fun getClimateData(city: String): Result<ClimateData>
    {
        return try {

            val response = api.getClimateData(city)
            Result.success(response)
        
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getIrrigationList(idPlot: Int): Result<List<IrrigationData>>
    {
        return try {

            val response = api.getIrrigationList(idPlot)
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

    // Comments
    suspend fun createComment(commentText : String, irrigationId: Int): Result<String> {
        return try {
            val response = api.createComment(commentText, irrigationId)
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

    suspend fun deleteComment(commentId: Int): Result<String> {
        return try {
            val response = api.deleteComment(commentId)
            Result.success(response)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Users *************

    // Create User
    suspend fun createUser(
        userName:String,
        userEmail:String,
        userPass:String,
        userIsAdmin: Boolean,
        plotCity: String,
        plotDesc: String,
        cropType: String

    ): Result<String> {
        return try {
            val response = api.createUser(
                userName,
                userEmail,
                userPass,
                userIsAdmin,
                plotCity,
                plotDesc,
                cropType
            )
            Result.success(response)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateUser(
        userId:Int,
        userName:String,
        userEmail:String,
        userPass:String,
        userIsAdmin: Boolean,
        plotCity: String,
        plotDesc: String,
        cropType: String
    ): Result<String> {
        return try {
            val response = api.updateUser(
                userId,
                userName,
                userEmail,
                userPass,
                userIsAdmin,
                plotCity,
                plotDesc,
                cropType
            )
            Result.success(response)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun loginUser(
        email: String,
        password: String
    ): Result<LoginData> {
        return try {
            val response = api.loginUser(email, password)
            Result.success(response)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteUser(userId:Int): Result<String> {
        return try {
            val response = api.deleteUser(userId)
            Result.success(response)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getBackofficeInfo(): Result<BackofficeData>
    {
        return try {

            val response = api.getBackofficeData()
            Result.success(response)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }


}