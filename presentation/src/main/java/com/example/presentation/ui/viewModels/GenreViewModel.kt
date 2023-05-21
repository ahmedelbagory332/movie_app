package com.example.presentation.ui.viewModels


import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.*
import com.example.domain.model.GenreItemModel
import com.example.domain.model.GenreModel
import com.example.domain.use_case.GetGenreUseCase
import com.example.domain.utils.GenreState
import com.example.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.*
import javax.inject.Inject


@HiltViewModel
class GenreViewModel @Inject constructor(
    val getGenreUseCase: GetGenreUseCase
) : ViewModel() {

    private val _state = mutableStateOf(GenreState())
    private var _selectedGenre = mutableStateOf(GenreItemModel())

    val genres: State<GenreState>
        get() = _state

    val selectedGenre: State<GenreItemModel>
        get() = _selectedGenre

    fun setSelectedGenre(selectedGenre: GenreItemModel){
        _selectedGenre.value = selectedGenre
        _selectedGenre.value = _selectedGenre.value
    }


    init {
        getGenre()
    }

    private fun getGenre() {
        getGenreUseCase(language = Locale.getDefault().language).onEach { result ->
            when (result) {
                is Resource.Success -> {

                    _state.value = GenreState(
                        genre = result.data?.genres ?: emptyList()
                    )

                }
                is Resource.Error -> {
                    _state.value = GenreState(
                        error = result.message ?: "An unexpected error happened"
                    )
                }
                is Resource.Loading -> {
                    _state.value = GenreState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)

    }

}