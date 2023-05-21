package com.example.domain.repository

import com.example.domain.model.GenreModel
import com.example.domain.model.MovieDetailsModel
import com.example.domain.model.MovieModel

interface MovieDetailsRepository {

    suspend fun getRemoteMovieDetails(language: String,movieId:String): MovieDetailsModel

}