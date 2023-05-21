package com.example.domain.use_case


import com.example.domain.model.MovieDetailsModel
import com.example.domain.model.MovieModel
import com.example.domain.repository.MovieDetailsRepository
import com.example.domain.repository.MoviesRepository
import com.example.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDetailsUseCase  @Inject constructor(
    private val repository: MovieDetailsRepository,

    ) {
    operator fun invoke(language: String,id:String): Flow<Resource<MovieDetailsModel>> = flow {
        try {
            emit(Resource.Loading<MovieDetailsModel>())
            val movie = repository.getRemoteMovieDetails(language,id)
            emit(Resource.Success<MovieDetailsModel>(movie))
        } catch(e: Exception) {
            emit(Resource.Error<MovieDetailsModel>("${e.localizedMessage} : An unexpected error happened"))
        }
    }
}