package com.halic.ilerimobilfinal

import android.os.Build
import android.os.Build.VERSION
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.halic.ilerimobilfinal.model.MovieModel

class MovieDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movie = if (VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("movie", MovieModel::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("movie")
        }

        if (movie != null) {
            Glide.with(this)
                .load(movie.poster)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(findViewById(R.id.header_image))

            val title = "${movie.id} - ${movie.name}"
            findViewById<TextView>(R.id.title).text = title

            findViewById<TextView>(R.id.description).text = movie.description

            val info = "(${movie.release}, ${movie.playtime})"
            findViewById<TextView>(R.id.info).text = info

            findViewById<TextView>(R.id.plot).text = movie.plot
        }
    }
}