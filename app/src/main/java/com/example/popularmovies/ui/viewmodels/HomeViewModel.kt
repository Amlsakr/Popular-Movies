package com.example.popularmovies.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.popularmovies.common.utill.Constant
import com.example.popularmovies.common.state.UiState
import com.example.popularmovies.di.MainDispatcher
import com.example.popularmovies.domain.model.Movie
import com.example.popularmovies.domain.usecase.GetMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher
) : ViewModel() {


    private val _movieListState = MutableLiveData<UiState<List<Movie>>>(UiState.Init())
    val movieListState: LiveData<UiState<List<Movie>>> = _movieListState

  //  private var nextPage = Constant.START_PAGE
    private var moviesList: MutableList<Movie> = mutableListOf()

    val isLoading: Boolean
        get() = _movieListState.value is UiState.Loading || _movieListState.value is UiState.LoadMore

    var isLastPage: Boolean = false

    init {
        loadMovies()
    }

    fun loadMovies(page:Int = 1) {

        getMovieUseCase(page)
            .onStart {
                _movieListState.value =  if (page == Constant.START_PAGE) UiState.Loading() else UiState.LoadMore()
            }.map {
                if (page != Constant.START_PAGE && it.isEmpty()) isLastPage = true
               // nextPage++
                moviesList.addAll(it)
                moviesList.toList()
            }.map {
                if (it.isEmpty()) {
                    UiState.Empty()
                }else {
                    UiState.Success(it)
                }
            }.onEach {
                _movieListState.value = it
            }.catch {
                Timber.e(it)
                if (moviesList.isEmpty()) {
                    _movieListState.value = UiState.Error(it.message)
                } else {
                    _movieListState.value = UiState.SoftError(it.message)
                }
            }.flowOn(mainDispatcher)
            .launchIn(viewModelScope)

    }


    fun resetList() {
      //  nextPage = Constant.START_PAGE
        moviesList.clear()
    }
}