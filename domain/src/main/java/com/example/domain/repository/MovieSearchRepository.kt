package com.example.domain.repository

import com.example.domain.model.MovieModel

interface MovieSearchRepository {

    suspend fun getRemoteSearchMovie(language: String,page: Int,movieName:String): MovieModel

}