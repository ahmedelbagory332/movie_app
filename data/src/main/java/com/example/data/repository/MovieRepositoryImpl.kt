package com.example.data.repository

import com.example.data.mapper.MovieMapper
import com.example.data.remote.MovieApi
import com.example.domain.model.MovieModel
import com.example.domain.repository.MoviesRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi,
    private val movieMapper: MovieMapper,
) : MoviesRepository {


    override suspend fun getRemoteMovie(language: String,page: Int,genresId:String): MovieModel {
        return movieMapper.fromRemoteMoviesToMoviesModel(api.getMovies(genresId = genresId,page= page,language=language))
    }


}