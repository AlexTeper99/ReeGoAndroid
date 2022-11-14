package com.example.reegoandroid.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.reegoandroid.R
import com.example.reegoandroid.viewmodels.node.UserData

class UserAdapter(var userList: MutableList<UserData>,  var onClick : (Int) -> Unit) : RecyclerView.Adapter<UserAdapter.UserHolder>() {

    class UserHolder (v: View) : RecyclerView.ViewHolder(v) {

        private var view: View

        init {
            this.view = v
        }

        fun setUser(user: UserData){

            var userNameView : TextView = view.findViewById(R.id.txtUserName)
            userNameView.text = user.name

            var userEmailView : TextView = view.findViewById(R.id.txtUserEmail)
            userEmailView.text = user.email
        }

        fun getCard() : CardView {
            return view.findViewById(R.id.userCard)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false)
        return (UserHolder(view))
    }

    override fun onBindViewHolder(holder: UserHolder, i: Int) {

        holder.setUser(
            userList[i]
        )

        holder.getCard().setOnClickListener{
            onClick(i)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}