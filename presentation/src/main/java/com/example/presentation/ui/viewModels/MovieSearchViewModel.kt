package com.example.presentation.ui.viewModels


import androidx.compose.runtime.*
import androidx.lifecycle.*
import com.example.domain.model.MovieModel
import com.example.domain.use_case.MovieSearchUseCase
import com.example.domain.utils.MovieSearchState
import com.example.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class MovieSearchViewModel @Inject constructor(
    val getMovieSearchUseCase: MovieSearchUseCase
) : ViewModel() {

    private val _state = mutableStateOf(MovieSearchState())
    val showPlaceHolder = mutableStateOf(true)
    val emptyList = mutableStateOf(false)
    val query = mutableStateOf("")

    val movies: State<MovieSearchState>
        get() = _state


    fun getSearchMovie(language: String, movieName: String) {
        getMovieSearchUseCase(movieName = movieName, language = language).onEach { result ->
            when (result) {
                is Resource.Success -> {

                    _state.value = MovieSearchState(
                        movies = result.data
                    )

                }
                is Resource.Error -> {
                    _state.value = MovieSearchState(
                        error = result.message ?: "An unexpected error happened"
                    )
                }
                is Resource.Loading -> {
                    _state.value = MovieSearchState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)

    }

}