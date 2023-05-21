package com.example.domain.repository

import com.example.domain.model.GenreModel
import com.example.domain.model.MovieModel

interface MoviesRepository {

    suspend fun getRemoteMovie(language: String,page: Int,genresId:String): MovieModel

}