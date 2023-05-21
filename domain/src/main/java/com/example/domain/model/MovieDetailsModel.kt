package com.example.domain.model



data class MovieDetailsModel(
    val id: Long = 0L,
    val posterUrl: String? = "",
    val name: String? = "",
    val tagline: String? = "",
    val releaseDate: String? = "",
    val runtime: Long? = 0L,
    val overview: String? = "",
    val genres: List<Genre>? = emptyList(),
    val productionCompanies: List<ProductionCompany>? = emptyList(),
    )

data class Genre(
    val id: Long = 0L,
    val name: String = "",
)

data class ProductionCompany(
    val id: Long = 0L,
    val logoPath: String = "",
    val name: String = "",
    val originCountry: String = "",
)