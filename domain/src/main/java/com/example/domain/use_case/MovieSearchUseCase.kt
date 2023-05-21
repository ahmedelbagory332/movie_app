package com.example.domain.use_case


import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.domain.model.MovieItem
import com.example.domain.model.MovieModel
import com.example.domain.pagingSource.MoviesPagingSource
import com.example.domain.pagingSource.MoviesSearchPagingSource
import com.example.domain.repository.MovieSearchRepository
import com.example.domain.repository.MoviesRepository
import com.example.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieSearchUseCase  @Inject constructor(
    private val repository: MovieSearchRepository,

    ) {
    operator fun invoke(language: String,movieName:String): Flow<Resource<Pager<Int, MovieItem>>> = flow {
        try {
            emit(Resource.Loading<Pager<Int, MovieItem>>())
             val getMovies  = Pager(
                config = PagingConfig(
                    pageSize = 10,
                ),
                pagingSourceFactory = {
                    MoviesSearchPagingSource(repository,movieName,language)
                }
            )
            emit(Resource.Success<Pager<Int, MovieItem>>(getMovies))
        } catch(e: Exception) {
            emit(Resource.Error<Pager<Int, MovieItem>>("${e.localizedMessage} : An unexpected error happened"))
        }
    }
}