package com.example.domain.use_case


import com.example.domain.model.GenreModel
import com.example.domain.repository.GenreRepository
import com.example.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetGenreUseCase  @Inject constructor(
    private val repository: GenreRepository,

    ) {
    operator fun invoke(language: String): Flow<Resource<GenreModel>> = flow {
        try {
            emit(Resource.Loading<GenreModel>())
            val genres = repository.getRemoteGenre(language)
            emit(Resource.Success<GenreModel>(genres))
        } catch(e: Exception) {
            emit(Resource.Error<GenreModel>("${e.localizedMessage} : An unexpected error happened"))
        }
    }
}