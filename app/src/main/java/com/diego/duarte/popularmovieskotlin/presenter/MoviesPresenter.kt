package com.diego.duarte.popularmovieskotlin.presenter

import androidx.recyclerview.widget.GridLayoutManager
import com.diego.duarte.popularmovieskotlin.model.data.Movie
import com.diego.duarte.popularmovieskotlin.model.interactors.MoviesInteractor
import com.diego.duarte.popularmovieskotlin.view.adapters.movies.MovieItemView
import com.diego.duarte.popularmovieskotlin.view.fragments.movies.MoviesView
import io.reactivex.rxjava3.observers.DisposableObserver
import kotlin.collections.ArrayList

class MoviesPresenter (private val interator: MoviesInteractor, private val view: MoviesView) : BasePresenter() {


    private var page: Int = 1
    private var isLoading = false
    private var movies: ArrayList<Movie> = ArrayList()

    fun getMovies() {
        isLoading = true
        val observer = MoviesListObserver()
        interator.getPopularMovies(page, MoviesListObserver())
    }

    fun getNextMoviesPage(layoutManager: GridLayoutManager) {

        if(!isLoading)
        if (layoutManager.findLastVisibleItemPosition() == layoutManager.itemCount - 11) {
            page++
            getMovies()

        }
    }

    val itemCount: Int
        get() = movies.size

    fun addItem(movie: Movie) {
        movies.add(movie)

    }

    fun onItemClicked(pos: Int) {
        val movie = movies[pos]
        //Toast.makeText(view., movie.title, Toast.LENGTH_SHORT).show()
    }

    fun onBindItemView(itemView: MovieItemView, pos: Int) {
        itemView.bindItem(movies[pos])
    }

    inner class MoviesListObserver : DisposableObserver<ArrayList<Movie>>() {
        override fun onNext(t: ArrayList<Movie>?) {
            //view.hideLoadingDialog()
            if (t != null) {
                for (movie in t) {
                    view.showMovie(movie)
                }
            }
            isLoading = false
        }

        override fun onError(e: Throwable?) {
            //view.hideLoadingDialog()
            //view.showError(it)
        }

        override fun onComplete() {
            TODO("Not yet implemented")
        }

    }


}