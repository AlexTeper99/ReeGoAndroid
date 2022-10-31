package com.example.reegoandroid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    private val userListViewModel : UserListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v =  inflater.inflate(R.layout.fragment_user_list, container, false)

        userRecyclerView = v.findViewById(R.id.recViewUsers)

        txtTitle = v.findViewById(R.id.txtUsersTitle)
        txtTitle.text = "Usuarios"

        return v
    }

    override fun onStart() {
        super.onStart()

        userListViewModel.getAllUsers()

        userListViewModel.userListLive.observe(viewLifecycleOwner) { userList ->
            
            userAdapter = UserAdapter(userList.toMutableList()){ pos ->
                val action = UserListFragmentDirections.actionUserListFragment2ToSingleUserFragment2()
                v.findNavController().navigate(action)
            }
            userAdapter.notifyDataSetChanged()

            userRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = userAdapter
            }

        }

    }

}