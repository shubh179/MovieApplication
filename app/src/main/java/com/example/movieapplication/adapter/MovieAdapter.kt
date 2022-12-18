package com.example.movieapplication.adapter

import android.app.ActionBar.LayoutParams
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapplication.R
import com.example.movieapplication.data.Movie
import com.example.movieapplication.utils.Constants.IMAGE_URL
import com.squareup.picasso.Picasso

class MovieAdapter(private var context: Context, private var movieList: MutableList<Movie>?) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.movie_rv_item,
            parent, false
        )

        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        Picasso.get().load(IMAGE_URL + movieList?.get(position)?.posterPath).into(holder.movieIV)

        holder.movieIV.setOnClickListener {
            Log.d("Adapter", "iddddd")
            showMovieDetailDialog(context, movieList?.get(position))
        }
    }

    override fun getItemCount(): Int {
        return movieList?.size ?: 0
    }

    private fun showMovieDetailDialog(context: Context, movie: Movie?) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        val view = LayoutInflater.from(context).inflate(R.layout.movie_detail_dialog, null)

        val movieBackdrop : ImageView = view.findViewById(R.id.movie_backdrop)
        val moviePoster : ImageView = view.findViewById(R.id.movie_poster)
        val movieTitle : TextView = view.findViewById(R.id.movie_title)
        val movieReleaseDate : TextView = view.findViewById(R.id.movie_release_date)
        val movieOverview : TextView = view.findViewById(R.id.movie_overview)
        val movieRating : RatingBar = view.findViewById(R.id.movie_rating)

        if (movie != null) {
            Picasso.get().load(IMAGE_URL + movie.backdropPath).into(movieBackdrop)
            Picasso.get().load(IMAGE_URL + movie.posterPath).into(moviePoster)
            movieTitle.text = movie.title
            movieReleaseDate.text = movie.releaseDate
            movieOverview.text = movie.overview
            movieRating.rating = movie.rating
        }

        alertDialogBuilder.setView(view)
        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.show()
        alertDialog.window?.setLayout(LayoutParams.MATCH_PARENT, 1500)
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieIV : ImageView = itemView.findViewById(R.id.idIVMovie)
    }
}