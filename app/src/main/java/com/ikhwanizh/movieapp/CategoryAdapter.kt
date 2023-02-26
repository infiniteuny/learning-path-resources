package com.ikhwanizh.movieapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CategoryAdapter(val listData: List<String>): RecyclerView.Adapter<CategoryAdapter.ListViewHolder>() {

    inner class ListViewHolder(listView: View) : RecyclerView.ViewHolder(listView) {
        val nameCategory : TextView = itemView.findViewById(R.id.tv_category)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.nameCategory.text = data
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}