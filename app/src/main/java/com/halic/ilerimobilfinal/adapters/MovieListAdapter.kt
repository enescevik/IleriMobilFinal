package com.halic.ilerimobilfinal.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.halic.ilerimobilfinal.R
import com.halic.ilerimobilfinal.model.MovieModel

class MovieListAdapter(
    private val movies: List<MovieModel>, private val itemClick: (MovieModel) -> Unit
) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>(), Filterable {

    private var filteredData = listOf<MovieModel>()

    enum class SortType { NameAsc, NameDesc, IdAsc, IdDesc }

    private var sortType: SortType = SortType.IdAsc

    init {
        filteredData = movies
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.card_layout, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(filteredData[position])
    }

    override fun getItemCount() = filteredData.size

    class ViewHolder(view: View, private val itemClick: (MovieModel) -> Unit) :
        RecyclerView.ViewHolder(view) {

        fun bindData(movie: MovieModel) {
            with(movie) {
                Glide
                    .with(itemView.context)
                    .load(movie.poster)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(itemView.findViewById(R.id.header_image))

                val title = "${movie.id} - ${movie.name}"
                itemView.findViewById<TextView>(R.id.title).text = title

                itemView.findViewById<TextView>(R.id.subhead).text = movie.playtime

                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()

                filteredData = if (charSearch.isEmpty()) {
                    movies
                } else {
                    val result = ArrayList<MovieModel>()
                    for (row in movies) {
                        if (row.name.contains(charSearch)) {
                            result.add(row)
                        }
                    }
                    result
                }

                return FilterResults().apply { values = filteredData }
            }

            @Suppress("UNCHECKED_CAST")
            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredData = results?.values as List<MovieModel>
                notifyDataSetChanged()
            }
        }
    }

    fun getSortType(): SortType = sortType

    @SuppressLint("NotifyDataSetChanged")
    fun setSortType(newSortType: SortType) {
        sortType = newSortType

        when (newSortType) {
            SortType.NameAsc -> {
                filteredData = ArrayList(filteredData.sortedBy { it.name })
            }

            SortType.NameDesc -> {
                filteredData = ArrayList(filteredData.sortedByDescending { it.name })
            }

            SortType.IdAsc -> {
                filteredData = ArrayList(filteredData.sortedBy { it.id })
            }

            SortType.IdDesc -> {
                filteredData = ArrayList(filteredData.sortedByDescending { it.id })
            }
        }

        notifyDataSetChanged()
    }
}