package com.example.domain.utils


import androidx.paging.Pager
import com.example.domain.model.MovieItem
import com.example.domain.model.MovieModel


data class MovieState (
    val isLoading: Boolean = false,
    val movies : Pager<Int, MovieItem>? = null ,
    val error: String = ""
)
