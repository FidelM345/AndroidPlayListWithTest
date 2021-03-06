package com.example.androidplaylist.playlist

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.androidplaylist.R


class MyPlaylistRecyclerViewAdapter(
        val values: List<PlaylistItem>,
        private val listener:(String)->Unit

) : RecyclerView.Adapter<MyPlaylistRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.playlist_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.title.text = item.name
        holder.category.text = item.category
        holder.image.setImageResource(item.image)
        holder.root.setOnClickListener { listener(item.id) }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.play_list_title)
        val category: TextView = view.findViewById(R.id.play_list_category)
        val image: ImageView = view.findViewById(R.id.play_list_image)
        val root:View=view.findViewById(R.id.playlist_item_root)


    }
}