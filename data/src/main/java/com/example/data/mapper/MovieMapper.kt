package com.example.data.mapper


import com.example.data.dto.GenreMovieDetailsDto
import com.example.data.dto.MovieDto
import com.example.data.dto.MovieItemDto
import com.example.domain.model.Genre
import com.example.domain.model.MovieItem
import com.example.domain.model.MovieModel
import javax.inject.Inject

class MovieMapper @Inject constructor() {


    fun fromRemoteMoviesToMoviesModel(obj: MovieDto): MovieModel {
        return MovieModel(
            moveList = fromRemoteMovieItemDtoToMovieItem(obj.moveList),
            page = obj.page,
            totalPages = obj.totalPages,
            totalResults = obj.totalResults
        )

    }

    private fun fromRemoteMovieItemDtoToMovieItem(obj: List<MovieItemDto>): List<MovieItem> {
        return obj.map {
            MovieItem(
                id = it.id,
                posterUrl = it.posterPath,
                name = it.title,
            )
        }

    }


}