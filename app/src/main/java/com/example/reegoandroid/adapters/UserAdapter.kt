package com.example.reegoandroid.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.reegoandroid.R
import com.example.reegoandroid.viewmodels.node.UserData

class UserAdapter(var userList: MutableList<UserData>) : RecyclerView.Adapter<UserAdapter.UserHolder>() {

    class UserHolder (v: View) : RecyclerView.ViewHolder(v) {

        private var view: View

        init {
            this.view = v
        }

        fun setUser(name: String, email: String){
            var userNameView : TextView = view.findViewById(R.id.txtUserName)
            userNameView.text = name

            var userEmailView : TextView = view.findViewById(R.id.txtUserEmail)
            userEmailView.text = email
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false)
        return (UserHolder(view))
    }

    override fun onBindViewHolder(holder: UserHolder, i: Int) {

        holder.setUser(
            userList[i].name,
            userList[i].email,
        )
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}