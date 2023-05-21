package com.example.domain.model



data class GenreModel(
    val genres: List<GenreItemModel> = emptyList(),
)



data class GenreItemModel(
    val id: Long = -1,
    val name: String = "",
)
