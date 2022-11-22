package com.example.reegoandroid.viewmodels

import androidx.lifecycle.ViewModel
import com.example.reegoandroid.viewmodels.node.NodeApi
import com.example.reegoandroid.viewmodels.node.NodeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SingleUserViewModel (private val nodeRepository: NodeRepository = NodeRepository(NodeApi.instance!!)): ViewModel() {
        init {}

    internal fun createUser(
        userName:String,
        userEmail:String,
        userPass:String,
        userIsAdmin: Boolean,
        plotCity: String,
        plotDesc: String,
        cropType: String
    ) {
        val scope = CoroutineScope(Dispatchers.Default)
        scope.launch {

            val result = nodeRepository.createUser(
                userName,
                userEmail,
                userPass,
                userIsAdmin,
                plotCity,
                plotDesc,
                cropType
            );

            result.onSuccess {
                println("Usuario Creado" )
            }.onFailure {
                println("Error en a llamada al api - Create User")
            }
        }
    }

    internal fun updateUser(
        userId:Int,
        userName:String,
        userEmail:String,
        userPass:String,
        userIsAdmin: Boolean,
        plotCity: String,
        plotDesc: String,
        cropType: String
    ) {
        val scope = CoroutineScope(Dispatchers.Default)
        scope.launch {

            val result = nodeRepository.updateUser(
                userId,
                userName,
                userEmail,
                userPass,
                userIsAdmin,
                plotCity,
                plotDesc,
                cropType
            );

            result.onSuccess {
                println("Usuario Actualizado" )
            }.onFailure {
                println("Error en a llamada al api - Update User")
            }
        }
    }

    internal fun deleteUser(userId: Int) {
        val scope = CoroutineScope(Dispatchers.Default)
        scope.launch {

            val result = nodeRepository.deleteUser(userId);

            result.onSuccess {
                println("Usuario Borrado" )
            }.onFailure {
                println("Error en a llamada al api - Delete User")
            }
        }
    }

    internal fun validateInputs(
        userName:String,
        userEmail:String,
        userPass:String,
        plotCity: String,
        plotDesc: String,
        cropType: String): Boolean {

        var validInputs = false

        validInputs = (
                userName  != "" &&
                userEmail != "" &&
                userPass  != "" &&
                plotCity  != "" &&
                plotDesc  != "" &&
                cropType  != ""
                )

        return  validInputs
    }

}