package com.example.presentation.ui.viewModels


import androidx.compose.runtime.*
import androidx.lifecycle.*
import com.example.domain.model.MovieModel
import com.example.domain.use_case.MoviesUseCase
import com.example.domain.utils.MovieState
import com.example.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class MoviesViewModel @Inject constructor(
    val getMoviesUseCase: MoviesUseCase
) : ViewModel() {

    private val _state = mutableStateOf(MovieState())

    val movies: State<MovieState>
        get() = _state


    fun getMovies(language: String, genresId: String) {
        getMoviesUseCase(genresId = genresId, language = language).onEach { result ->
            when (result) {
                is Resource.Success -> {

                    _state.value = MovieState(
                        movies = result.data
                    )

                }
                is Resource.Error -> {
                    _state.value = MovieState(
                        error = result.message ?: "An unexpected error happened"
                    )
                }
                is Resource.Loading -> {
                    _state.value = MovieState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)

    }

}