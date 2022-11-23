package com.example.reegoandroid.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.reegoandroid.R
import com.example.reegoandroid.viewmodels.node.UserData
//el adapter recibe una lista y una funcion que se va a ejecutar cuando se hace el onclick
class UserAdapter(var userList: MutableList<UserData>,  var onClick : (Int) -> Unit) : RecyclerView.Adapter<UserAdapter.UserHolder>() {
    //La funcion del holder es ser la parte del adapter que se comunica con el xml de item. La view que recibe es la instancia del item.
    //va a ejecutar funciones que se ejecuten cuando renderice los items.
    class UserHolder (v: View) : RecyclerView.ViewHolder(v) {
        //creo una vista
        private var view: View

        init {
            this.view = v
        }
        //esta funcion va a buscar el textView del item y lo setea con el valor que le pasaron por param
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
    //este metodo va a buscar el item.xml de la lista y se lo pasa al holder.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false)
        return (UserHolder(view))
    }
    //Cuando clickeas un item se llama a este metodo que invoca a tus metodos del holder
    override fun onBindViewHolder(holder: UserHolder, i: Int) {
        holder.setUser(
            userList[i]
        )

        holder.getCard().setOnClickListener{
            onClick(i)
        }
    }
    //devuelve el tamanio de la lista.
    override fun getItemCount(): Int {
        return userList.size
    }
}