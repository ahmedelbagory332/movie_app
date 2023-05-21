package com.example.data.repository

import android.util.Log
import com.example.data.mapper.MovieMapper
import com.example.data.mapper.SearchMovieMapper
import com.example.data.remote.MovieApi
import com.example.domain.model.MovieModel
import com.example.domain.repository.MovieSearchRepository
import com.example.domain.repository.MoviesRepository
import javax.inject.Inject

class MovieSearchRepositoryImpl @Inject constructor(
    private val api: MovieApi,
    private val movieMapper: SearchMovieMapper,
) : MovieSearchRepository {


    override suspend fun getRemoteSearchMovie(language: String,page: Int,movieName:String): MovieModel {
        return movieMapper.fromRemoteMoviesToMoviesModel(api.getSearchMovies(movieName,page,language))
    }


}