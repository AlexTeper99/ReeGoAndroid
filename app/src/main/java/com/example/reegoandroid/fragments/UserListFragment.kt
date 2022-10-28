package com.example.reegoandroid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reegoandroid.R
import com.example.reegoandroid.adapters.UserAdapter
import com.example.reegoandroid.viewmodels.UserListViewModel
import com.example.reegoandroid.viewmodels.node.UserData

class UserListFragment : Fragment() {
    lateinit var v: View
    private lateinit var txtTitle : TextView

    lateinit var userRecyclerView: RecyclerView
    lateinit var userAdapter: UserAdapter
    var users : MutableList<UserData> = mutableListOf()



    private val userListViewModel : UserListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v =  inflater.inflate(R.layout.fragment_user_list, container, false)

        users.add(UserData("20-20-20", "jose@mail.com",23,1,false,"Jose Perez","123","20-20-22"))
        users.add(UserData("20-20-20", "nose@mail.com",26,1,false,"Maria Toledo","123","20-20-22"))
        users.add(UserData("20-20-20", "nose@mail.com",26,1,false,"Maria Toledo","123","20-20-22"))
        users.add(UserData("20-20-20", "nose@mail.com",26,1,false,"Maria Toledo","123","20-20-22"))
        users.add(UserData("20-20-20", "nose@mail.com",26,1,false,"Maria Toledo","123","20-20-22"))
        users.add(UserData("20-20-20", "nose@mail.com",26,1,false,"Maria Toledo","123","20-20-22"))
        users.add(UserData("20-20-20", "nose@mail.com",26,1,false,"Maria Toledo","123","20-20-22"))
        users.add(UserData("20-20-20", "nose@mail.com",26,1,false,"Maria Toledo","123","20-20-22"))
        users.add(UserData("20-20-20", "nose@mail.com",26,1,false,"Maria Toledo","123","20-20-22"))
        users.add(UserData("20-20-20", "nose@mail.com",26,1,false,"Maria Toledo","123","20-20-22"))
        users.add(UserData("20-20-20", "nose@mail.com",26,1,false,"Maria Toledo","123","20-20-22"))
        users.add(UserData("20-20-20", "nose@mail.com",26,1,false,"Maria Toledo","123","20-20-22"))
        users.add(UserData("20-20-20", "nose@mail.com",26,1,false,"Maria Toledo","123","20-20-22"))
        users.add(UserData("20-20-20", "nose@mail.com",26,1,false,"Maria Toledo","123","20-20-22"))
        users.add(UserData("20-20-20", "nose@mail.com",26,1,false,"Maria Toledo","123","20-20-22"))
        users.add(UserData("20-20-20", "nose@mail.com",26,1,false,"Maria Toledo","123","20-20-22"))
        users.add(UserData("20-20-20", "nose@mail.com",26,1,false,"Maria Toledo","123","20-20-22"))
        users.add(UserData("20-20-20", "nose@mail.com",26,1,false,"Maria Toledo","123","20-20-22"))
        users.add(UserData("20-20-20", "nose@mail.com",26,1,false,"Maria Toledo","123","20-20-22"))

        userRecyclerView = v.findViewById(R.id.recViewUsers)

        txtTitle = v.findViewById(R.id.txtUsersTitle)
        txtTitle.text = "Usuarios"

        return v
    }

    override fun onStart() {
        super.onStart()

        // TODO GET LIST FROM API AND SET IT TO THE RECYCLER VIEW // live data issue
        //userListViewModel.getAllUsers()
        //userAdapter = UserAdapter(userListViewModel.userList)

        userAdapter = UserAdapter(users)
        userRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        userRecyclerView.adapter = userAdapter

    }

}