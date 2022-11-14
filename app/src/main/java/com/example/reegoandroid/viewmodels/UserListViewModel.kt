package com.example.reegoandroid.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reegoandroid.viewmodels.node.NodeApi
import com.example.reegoandroid.viewmodels.node.NodeRepository
import com.example.reegoandroid.viewmodels.node.UserData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserListViewModel(private val nodeRepository: NodeRepository = NodeRepository(NodeApi.instance!!))
    : ViewModel() {
    init {
        this@UserListViewModel.getAllUsers()
    }

    var userList: MutableList<UserData> = mutableListOf()

    val userListLive: MutableLiveData<List<UserData>> by lazy {
        MutableLiveData<List<UserData>>()
    }

    fun clearData() {
        userListLive.value = null
    }

    internal fun getAllUsers() {


        val scope = CoroutineScope(Dispatchers.Default)

        scope.launch(Dispatchers.Default) {
            val result = nodeRepository.getAllusers()

            result.onSuccess {

                userList = it
                // observe in view
                userListLive.postValue(it)

                println("User list, from Node Api")
                println("----- begin ------")
                for (user in userList) {
                    println(user.name + " " + user.email)
                }
                println("----- end ------")


            }.onFailure {
                println("ERROR En la llamada al Api /users")
                println("Error: ${it.message}")
            }
        }
    }

}