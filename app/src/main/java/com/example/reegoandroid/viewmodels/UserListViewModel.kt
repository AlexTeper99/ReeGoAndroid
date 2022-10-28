package com.example.reegoandroid.viewmodels

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

    internal fun setUserList(list: MutableList<UserData>) {
        this.userList = list
    }

    internal fun getAllUsers() {


        val scope = CoroutineScope(Dispatchers.Default)

        scope.launch(Dispatchers.Default) {
            val result = nodeRepository.getAllusers()

            result.onSuccess {
                val userList = it
                println("User list, from Node Api")
                println("---------------------------")

                println(userList[0].name)
                println(userList[1].name)
                println(userList[2].name)
                println(userList[3].name)

                this@UserListViewModel.setUserList(userList)

            }.onFailure {
                println("ERROR En la llamada al Api /users")
                println("Error: ${it.message}")
            }
        }
    }

}