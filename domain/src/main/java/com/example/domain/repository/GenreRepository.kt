package com.example.domain.repository

import com.example.domain.model.GenreModel

interface GenreRepository {

    suspend fun getRemoteGenre(language: String): GenreModel

}