package com.example.domain.utils


import com.example.domain.model.MovieDetailsModel


data class MovieDetailsState (
    val isLoading: Boolean = false,
    val movie : MovieDetailsModel = MovieDetailsModel()  ,
    val error: String = ""
)
