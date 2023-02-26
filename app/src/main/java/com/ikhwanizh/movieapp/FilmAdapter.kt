package com.ikhwanizh.movieapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class FilmAdapter(val listData: List<Film>): RecyclerView.Adapter<FilmAdapter.ListViewHolder>() {

    inner class ListViewHolder(listView: View) : RecyclerView.ViewHolder(listView) {
        val nameFilm : TextView = itemView.findViewById(R.id.tv_title_name_film)
        val dateFilm : TextView = itemView.findViewById(R.id.tv_date_film)
        val ratingFilm : TextView = itemView.findViewById(R.id.tv_rating_film)
        val image : ImageView = itemView.findViewById(R.id.iv_film)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_film, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        with(holder) {
            nameFilm.text = data.name
            dateFilm.text = data.date
            ratingFilm.text = data.rating
            image.setImageResource(data.image)
        }
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}