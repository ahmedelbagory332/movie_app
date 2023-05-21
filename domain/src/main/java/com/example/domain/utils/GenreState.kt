package com.example.domain.utils

import com.example.domain.model.GenreItemModel
import com.example.domain.model.GenreModel


data class GenreState (
    val isLoading: Boolean = false,
    val genre : List<GenreItemModel>  = emptyList(),
    val error: String = ""
)
