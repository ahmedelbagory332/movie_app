package com.example.presentation.ui.viewModels


import androidx.compose.runtime.*
import androidx.lifecycle.*
import com.example.domain.model.MovieDetailsModel
import com.example.domain.use_case.MovieDetailsUseCase
import com.example.domain.utils.MovieDetailsState
import com.example.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    val getMovieDetailsUseCase: MovieDetailsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(MovieDetailsState())

    val movie: State<MovieDetailsState>
        get() = _state





      fun getMovies(language: String,id:String) {
          getMovieDetailsUseCase(id= id,language = language).onEach { result ->
            when (result) {
                is Resource.Success -> {

                    _state.value = MovieDetailsState(
                        movie = result.data ?: MovieDetailsModel()
                    )

                }
                is Resource.Error -> {
                    _state.value = MovieDetailsState(
                        error = result.message ?: "An unexpected error happened"
                    )
                }
                is Resource.Loading -> {
                    _state.value = MovieDetailsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)

    }

}