package com.example.reegoandroid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reegoandroid.R
import com.example.reegoandroid.adapters.UserAdapter
import com.example.reegoandroid.viewmodels.UserListViewModel

class UserListFragment : Fragment() {
    lateinit var v: View
    private lateinit var txtTitle : TextView

    lateinit var userRecyclerView: RecyclerView
    lateinit var userAdapter: UserAdapter

    lateinit var btnAddUser: Button

    private val userListViewModel : UserListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v =  inflater.inflate(R.layout.fragment_user_list, container, false)

        userRecyclerView = v.findViewById(R.id.recViewUsers)

        txtTitle = v.findViewById(R.id.txtUsersTitle)
        txtTitle.text = "Usuarios"
        btnAddUser = v.findViewById(R.id.addUserBtn)

        return v
    }

    override fun onStart() {
        super.onStart()

        userListViewModel.getAllUsers()
        //observe == listener del live data en cuanto a cambios en ese array.(se ejecuta con el postvalue)
        userListViewModel.userListLive.observe(viewLifecycleOwner) { userList ->

            //Adapters son para manejar listas y visuales con las listas de objetos de la bd
            userAdapter = UserAdapter(userList.toMutableList()) { pos: Int ->
                val action = UserListFragmentDirections.actionUserListFragment2ToSingleUserFragment2(

                    userList[pos].userId,
                    userList[pos].email,
                    userList[pos].password,
                    userList[pos].idCrop,
                    userList[pos].name,
                    userList[pos].isAdmin,
                    true,
                    userList[pos].plotCity,
                    userList[pos].plotDescription,
                    userList[pos].cropType,

                )
                //declarando accion de clickeo cuando se toca un usuario (navigate a pantalla de modificacion)
                v.findNavController().navigate(action)
            }

            userAdapter.notifyDataSetChanged()

            userRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = userAdapter
            }

        }

        // Create a user / redirect to create user
        btnAddUser.setOnClickListener {
            println("add a user pressed")

             val action =
                UserListFragmentDirections.actionUserListFragment2ToSingleUserFragment2(
                    0,
                    "",
                    "",
                    0,
                    "",
                    false,
                    isEdit = false,
                    "",
                    "",
                    ""
                )

            v.findNavController().navigate(action)
        }

    }

    override fun onDestroy(){
        super.onDestroy()
        userListViewModel.clearData()
    }

}