package com.example.movieapplication.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapplication.R
import com.example.movieapplication.data.Movie
import com.example.movieapplication.dialog.MovieDetailDialog
import com.example.movieapplication.utils.Constants.IMAGE_URL
import com.squareup.picasso.Picasso

class MovieAdapter(private var movieList: List<Movie>?) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.movie_rv_item,
            parent, false
        )

        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieAdapter.MovieViewHolder, position: Int) {
        Picasso.get().load(IMAGE_URL + movieList?.get(position)?.posterPath).into(holder.movieIV)

        holder.movieIV.setOnClickListener {
            Log.d("Adapter", "iddddd")
//            MovieDetailDialog(this, movieList?.get(position)).show()
        }
    }

    override fun getItemCount(): Int {
        return movieList?.size ?: 0
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieIV: ImageView = itemView.findViewById(R.id.idIVMovie)
    }
}