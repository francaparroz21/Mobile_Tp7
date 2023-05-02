package com.example.mobile_tp6.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mobile_tp6.R
import com.example.mobile_tp6.databinding.ItemRecyclerBinding
import com.example.mobile_tp6.service.model.Movie

class MovieAdapter (private val movies: List<Movie>): RecyclerView.Adapter<MovieAdapter.ViewHolder>(){

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val binding = ItemRecyclerBinding.bind(itemView)

        fun bind(movie: Movie){
            binding.id.text = itemView.context.getString(R.string.item_id)
            binding.title.text = itemView.context.getString(R.string.item_title)
            binding.date.text = itemView.context.getString(R.string.item_date)

            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/original" + movie.poster_path)
                .into(binding.image)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_recycler,parent,false))
    }

    override fun onBindViewHolder(holder: MovieAdapter.ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }


}