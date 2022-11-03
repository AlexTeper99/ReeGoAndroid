package com.example.reegoandroid.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.reegoandroid.R
import com.example.reegoandroid.viewmodels.node.NoteData

class NoteAdapter(var noteList: MutableList<NoteData>, var onClick : (Int) -> Unit) : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {
    
    class NoteHolder (v: View) : RecyclerView.ViewHolder(v) {
        private var view: View
        init {
            this.view = v
        }

        fun setItem(noteText: String) {
            var txtComment : TextView = view.findViewById(R.id.txtComment)
            txtComment.text = noteText
        }

        // get the new view // Single Comment Card
        fun getCard() : CardView {
            return view.findViewById(R.id.commentCard)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        // Note holder is item_comment
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
        return (NoteHolder(view))
    }

    override fun onBindViewHolder(holder: NoteHolder, pos: Int) {
        holder.setItem(noteList[pos].text)

        holder.getCard().setOnClickListener {
            onClick(pos)
        }
    }

    override fun getItemCount(): Int {
        return  noteList.size
    }

}