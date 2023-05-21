package com.example.data.mapper


import com.example.data.dto.*
import com.example.domain.model.*
import javax.inject.Inject

class MovieDetailsMapper @Inject constructor() {


    fun fromRemoteMovieDetailsToMovieDetailsModel(obj: MovieDetailsDto): MovieDetailsModel {
        return MovieDetailsModel(
            id = obj.id,
            posterUrl = obj.backdropPath,
            name = obj.originalTitle,
            tagline = obj.tagline,
            releaseDate = obj.releaseDate,
            runtime = obj.runtime,
            overview = obj.overview,
            genres = fromRemoteGenreMovieDetailsDtoToItemGenre(obj.genres),
            productionCompanies = fromRemoteProductionCompanyDtoToProductionCompany(obj.productionCompanies),

        )


    }

    private fun fromRemoteGenreMovieDetailsDtoToItemGenre(obj: List<GenreMovieDetailsDto>): List<Genre> {
        return obj.map {
            Genre(
                id = it.id,
                name = it.name,
            )
        }

    }

    private fun fromRemoteProductionCompanyDtoToProductionCompany(obj: List<ProductionCompanyDto>): List<ProductionCompany> {
        return obj.map {
            ProductionCompany(
                id = it.id,
                name = it.name,
                logoPath = it.logoPath?: "",
                originCountry = it.originCountry,
            )
        }

    }


}