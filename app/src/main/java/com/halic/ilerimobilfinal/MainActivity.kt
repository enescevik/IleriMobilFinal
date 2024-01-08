package com.halic.ilerimobilfinal

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RadioButton
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.halic.ilerimobilfinal.adapters.MovieListAdapter
import com.halic.ilerimobilfinal.components.CustomExitDialog
import com.halic.ilerimobilfinal.view_model.MovieViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var appMenu: Menu
    private lateinit var rvVideo: RecyclerView

    private lateinit var viewModel: MovieViewModel
    private lateinit var movieListAdapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onBackPressedDispatcher.addCallback { customExitDialog() }

        rvVideo = findViewById(R.id.rvVideo)
        val rlLoading = findViewById<RelativeLayout>(R.id.rlLoading)
        val detailIntent = Intent(this, MovieDetailActivity::class.java)

        val swipeRefresh = findViewById<SwipeRefreshLayout>(R.id.swipeRefresh)
        swipeRefresh.setOnRefreshListener {
            swipeRefresh.isRefreshing = false

            rlLoading.visibility = View.VISIBLE
            rvVideo.visibility = View.GONE

            viewModel.fetchMovies()
        }

        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]

        viewModel.moviesLiveData.observe(this) { movies ->
            if (movies.isNullOrEmpty()) {
                startActivity(Intent(this, EmptyDataActivity::class.java))
                finish()
            } else {
                movieListAdapter = MovieListAdapter(movies) {
                    detailIntent.putExtra("movie", it)
                    startActivity(detailIntent)
                }
                rvVideo.adapter = movieListAdapter

                rlLoading.visibility = View.GONE
                rvVideo.visibility = View.VISIBLE
            }
        }

        viewModel.fetchMovies()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        appMenu = menu
        menuInflater.inflate(R.menu.options_menu, menu)

        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.queryHint = getString(R.string.search_hint)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                movieListAdapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                movieListAdapter.filter.filter(newText)
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.list_type -> {
                val glm = rvVideo.layoutManager as GridLayoutManager
                glm.spanCount = if (glm.spanCount == 1) 2 else 1
                rvVideo.adapter = movieListAdapter

                appMenu.findItem(R.id.list_type)
                    .setIcon(if (glm.spanCount == 1) R.drawable.view_list else R.drawable.view_grid)
            }

            R.id.sort -> {
                sortDialog()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun customExitDialog() = CustomExitDialog().show(this)

    private fun sortDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.sort_type_dialog)

        val optNameAsc = dialog.findViewById<RadioButton>(R.id.optNameAsc)
        val optNameDesc = dialog.findViewById<RadioButton>(R.id.optNameDesc)
        val optIdAsc = dialog.findViewById<RadioButton>(R.id.optIdAsc)
        val optIdDesc = dialog.findViewById<RadioButton>(R.id.optIdDesc)

        when (movieListAdapter.getSortType()) {
            MovieListAdapter.SortType.NameAsc -> optNameAsc.isChecked = true
            MovieListAdapter.SortType.NameDesc -> optNameDesc.isChecked = true
            MovieListAdapter.SortType.IdAsc -> optIdAsc.isChecked = true
            MovieListAdapter.SortType.IdDesc -> optIdDesc.isChecked = true
        }

        val dialogButtonYes = dialog.findViewById<TextView>(R.id.textViewYes)
        val dialogButtonNo = dialog.findViewById<TextView>(R.id.textViewNo)

        dialogButtonNo.setOnClickListener {
            dialog.dismiss()
        }

        dialogButtonYes.setOnClickListener {
            dialog.dismiss()

            if (optNameAsc.isChecked) {
                movieListAdapter.setSortType(MovieListAdapter.SortType.NameAsc)
            } else if (optNameDesc.isChecked) {
                movieListAdapter.setSortType(MovieListAdapter.SortType.NameDesc)
            } else if (optIdAsc.isChecked) {
                movieListAdapter.setSortType(MovieListAdapter.SortType.IdAsc)
            } else {
                movieListAdapter.setSortType(MovieListAdapter.SortType.IdDesc)
            }

            rvVideo.adapter = movieListAdapter
        }

        dialog.setCancelable(false)
        dialog.show()
    }
}