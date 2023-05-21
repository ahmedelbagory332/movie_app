package com.example.data.repository

import com.example.data.mapper.MovieDetailsMapper
import com.example.data.remote.MovieApi
import com.example.domain.model.MovieDetailsModel
import com.example.domain.repository.MovieDetailsRepository
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val api: MovieApi,
    private val movieDetailsMapper: MovieDetailsMapper,
) : MovieDetailsRepository {


    override suspend fun getRemoteMovieDetails(language: String,movieId:String): MovieDetailsModel {
        return movieDetailsMapper.fromRemoteMovieDetailsToMovieDetailsModel(api.getMovieDetails(movieId,language))
    }


}