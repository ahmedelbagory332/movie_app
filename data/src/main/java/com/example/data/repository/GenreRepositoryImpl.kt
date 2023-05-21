package com.example.data.repository

import android.util.Log
import com.example.data.mapper.GenreMapper
import com.example.data.remote.MovieApi
import com.example.domain.model.GenreModel
import com.example.domain.repository.GenreRepository
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    private val api: MovieApi,
    private val genreMapper: GenreMapper,
) : GenreRepository {
    override suspend fun getRemoteGenre(language: String): GenreModel {
//        Log.d("TAG",  "begotest:${genreMapper.fromRemoteGenreToGenreModel(api.getGenres()).genres!!.size} ")
       return genreMapper.fromRemoteGenreToGenreModel(api.getGenres(language))
    }


}