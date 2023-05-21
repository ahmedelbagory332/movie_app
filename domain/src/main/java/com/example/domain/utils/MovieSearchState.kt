package com.example.domain.utils


import androidx.paging.Pager
import com.example.domain.model.MovieItem


data class MovieSearchState (
    val isLoading: Boolean = false,
    val movies : Pager<Int, MovieItem>? = null,
    val error: String = ""
)
