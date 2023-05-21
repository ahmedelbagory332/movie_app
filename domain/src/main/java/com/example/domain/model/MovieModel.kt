package com.example.domain.model



data class MovieModel(
    val moveList: List<MovieItem> = emptyList(),
    val page: Long = 0L,
    val totalPages: Long = 0L,
    val totalResults: Long = 0L,


    )

data class MovieItem(
    val id: Long = 0L,
    val posterUrl: String? = "",
    val name: String = "",
)
