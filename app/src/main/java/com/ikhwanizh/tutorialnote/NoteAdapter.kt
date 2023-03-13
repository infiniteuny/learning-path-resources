package com.ikhwanizh.tutorialnote

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ikhwanizh.tutorialnote.data.Note

class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private val list = mutableListOf<Note>()

    class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tv_title =itemView.findViewById<TextView>(R.id.text_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)

        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = list[position]
        holder.tv_title.text = note.title
    }

    override fun getItemCount() = list.size

    fun setData(data: List<Note>){
        list.apply {
            clear()
            addAll(data)
        }
    }
}